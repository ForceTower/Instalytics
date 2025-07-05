//
//  LinkFacebookAccountViewModel.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 04/07/25.
//

import Combine
@preconcurrency import InstalyticsKit
import FacebookLogin
import FacebookCore

//extension CheckFacebookLoginTokenUseCase: @unchecked @retroactive Sendable {}

@MainActor
class LinkFacebookAccountViewModel : ObservableObject {
    private let checkFacebookLogin: CheckFacebookLoginTokenUseCase
    
    var router: RootRouter? = nil
    @Published var isLoading: Bool = false
    private let manager: LoginManager = .init()
    
    private let permissions = [
        "instagram_basic",
        "instagram_content_publish",
        "instagram_manage_comments",
        "instagram_manage_insights",
        "instagram_manage_messages",
        "pages_show_list",
        "pages_read_engagement",
        "ads_management",
        "ads_read"
    ]
    
    init(checkFacebookLogin: CheckFacebookLoginTokenUseCase = ApplicationComponents.shared.checkFacebookLoginTokenUseCase) {
        self.checkFacebookLogin = checkFacebookLogin
    }
    
    func facebookLogin() {
        manager.logIn(permissions: permissions, from: nil) { [weak self] result, err in
            if let err = err {
                self?.onFailedToLogin(with: err)
                return
            }
            
            if let result = result, !result.isCancelled, let token = result.token {
                self?.onConnected(with: token)
            } else {
                self?.onLoginCancelled()
            }
        }
    }
    
    private func onFailedToLogin(with error: Error) {
        print("Failed to login: \(error)")
        isLoading = false
    }
    
    private func onLoginCancelled() {
        print("Cancelled")
        isLoading = false
    }
    
    private func onConnected(with token: AccessToken) {
        Task {
            await checkProfile(token: token.tokenString)
        }
    }
    
    private func checkProfile(token: String) async {
        do {
            let result = try await checkFacebookLogin.checkTokenUsable(token: token)
            handleProfileUsableResult(result.boolValue)
        } catch {
            manager.logOut()
            onFailedToLogin(with: error)
        }
    }
    
    @MainActor
    private func handleProfileUsableResult(_ result: Bool) {
        if result {
            print("Profile is usable. Move to connected.")
            self.router?.state = .connected
        } else {
            manager.logOut()
            print("Profile is not usable. Teach.")
        }
    }
}
