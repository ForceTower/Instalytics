//
//  BootViewModel.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 04/07/25.
//

@preconcurrency import InstalyticsKit
import Combine

@MainActor
class BootViewModel : InstalyticsKit.AppBootViewModel, ObservableObject {
    private let initialRouteUseCase: GetInitialRouteUseCase
    var router: RootRouter? = nil
    @Published private(set) var initialRoute: AppInitialRoute? = nil
    
    init(initialRouteUseCase: GetInitialRouteUseCase = ApplicationComponents.shared.getInitialRouteUseCase) {
        self.initialRouteUseCase = initialRouteUseCase
        super.init(getInitialRouteUseCase: initialRouteUseCase)
    }
    
    func boot() {
        Task { await start() }
    }
    
    private func start() async {
        do {
            let initialRoute = try await initial()
            onInitialRoute(initialRoute)
        } catch {
            print("Error fetching initial route: \(error)")
        }
    }
    
    private func onInitialRoute(_ initialRoute: AppInitialRoute) {
        switch initialRoute {
        case .auth:
            router?.state = .login
        case .facebookLink:
            router?.state = .facebook
        case .home:
            router?.state = .connected
        }
    }
}
