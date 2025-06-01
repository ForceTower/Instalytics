//
//  AuthRootView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import SwiftUI

struct AuthRootView: View {
    @State var path: NavigationPath = .init()
    
    var body: some View {
        NavigationStack(path: $path) {
            LoginPlatformView(path: $path.animation(.easeOut))
        }
    }
}

#Preview {
    AuthRootView()
}
