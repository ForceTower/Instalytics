//
//  RootView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 28/05/25.
//

import SwiftUI

struct RootView: View {
    @EnvironmentObject var router: RootRouter
    
    var body: some View {
        switch router.state {
        case .initializing:
            BootView()
        case .login:
            AuthRootView()
        case .connected:
            HomeView()
        case .facebook:
            LinkFacebookAccountView()
        }
    }
}

#Preview {
    RootView()
        .environmentObject(RootRouter())
}
