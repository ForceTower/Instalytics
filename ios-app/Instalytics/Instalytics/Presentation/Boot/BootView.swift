//
//  BootView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 04/07/25.
//

import SwiftUI

struct BootView: View {
    @EnvironmentObject var router: RootRouter
    @StateObject private var vm: BootViewModel = .init()
    
    var body: some View {
        VStack {
            ProgressView()
            Text("Loading Instalytics...")
                .font(.headline)
        }.onAppear {
            vm.router = router
            vm.boot()
        }
    }
}

#Preview {
    BootView()
        .environmentObject(RootRouter())
}
