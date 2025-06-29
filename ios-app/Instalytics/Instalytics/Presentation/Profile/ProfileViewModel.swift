//
//  ProfileViewModel.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 29/06/25.
//

import InstalyticsKit
import Combine

class ProfileViewModel: InstalyticsKit.ProfilePostsViewModel, ObservableObject {
    private let fetchProfileUseCase: FetchConnectedUserProfileUseCase
    
    @Published private(set) var account: InstagramAccountUI? = nil
    @Published private(set) var photos: [Any] = []
    
    init(fetchProfileUseCase: FetchConnectedUserProfileUseCase = ApplicationComponents.shared.fetchConnectedUserProfile) {
        self.fetchProfileUseCase = fetchProfileUseCase
        super.init()
    }
    
    @MainActor
    func onAppear() {
        Task { await observeAccount() }
        Task { await observePhotos() }
        Task { await loadMe() }
    }

    
    @MainActor
    private func observeAccount() async {
        for await account in fetchProfileUseCase.me {
            self.account = account
        }
    }
    
    @MainActor
    private func observePhotos() async {
        for await photos in postsSnapshotList {
            self.photos = photos
        }
    }
    
    private func loadMe() async {
        do {
            try await fetchProfileUseCase.fetchMe()
        } catch {
            print("failed to fetch me: \(error)")
        }
    }
}
