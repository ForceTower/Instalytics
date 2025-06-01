//
//  WelcomeView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import SwiftUI

struct WelcomeView: View {
    @Binding var path: NavigationPath
    
    var body: some View {
        Text("Hello, World!")
    }
}

#Preview {
    @Previewable @State var path: NavigationPath = .init()
    WelcomeView(path: $path)
}
