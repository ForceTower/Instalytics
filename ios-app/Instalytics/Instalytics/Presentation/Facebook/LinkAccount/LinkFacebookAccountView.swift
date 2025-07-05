//
//  LinkFacebookAccountView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 04/07/25.
//

import SwiftUI
import FacebookLogin

struct LinkFacebookAccountView: View {
    @EnvironmentObject var router: RootRouter
    @StateObject var vm: LinkFacebookAccountViewModel = .init()
    
    var body: some View {
        VStack {
            Image(.illustrationWomanGraph)
                .resizable()
                .scaledToFit()
                .frame(maxWidth: .infinity)
                .ignoresSafeArea()
            
            Text("Connect your Instagram Account")
                .multilineTextAlignment(.center)
                .font(.headline)
                .padding(.horizontal)
                .ignoresSafeArea(edges: .top)
            
            Text("To unlock insights and compare your stats, connect your Instagram. We'll never post or share your data.")
                .multilineTextAlignment(.center)
                .font(.caption)
                .padding(.horizontal)
                .padding(.top, 8)
            
            if vm.isLoading {
                ProgressView()
                    .padding(.top, 32)
            } else {
                Button {
                    vm.isLoading = true
                    vm.facebookLogin()
                } label: {
                    Text("Link to Facebook")
                }
                .padding(.top, 16)
            }
            
            Spacer()
            
            Text("We respect your privacy. Most functionality of the app works on device and most data never leave your phone.")
                .multilineTextAlignment(.center)
                .font(.footnote)
                .padding(.horizontal)
        }
        .onAppear {
            vm.router = router
        }
    }
}
