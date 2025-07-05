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
        ZStack {
            ScrollView {
                VStack {
                    Image(.illustrationWomanGraph)
                        .resizable()
                        .scaledToFit()
                        .frame(maxWidth: .infinity)
                        .ignoresSafeArea(edges: .top)
                        .ignoresSafeArea(edges: .horizontal)
                    
                    Text("Connect your Instagram Account")
                        .multilineTextAlignment(.center)
                        .font(.title2)
                        .padding(.horizontal)
                        .padding(.top, 16)
                    
                    Text("To unlock insights and compare your stats, connect your Instagram. We'll never post or share your data.")
                        .multilineTextAlignment(.center)
                        .font(.callout)
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
                            Text("Link Facebook Account")
                        }
                        .padding(.top, 16)
                        .buttonStyle(.borderedProminent)
                    }
                }
                .frame(alignment: .top)
                .onAppear {
                    vm.router = router
                }
            }
            .frame(maxHeight: .infinity)
            .ignoresSafeArea()
        }
        .scrollDisabled(true)
    }
}

#Preview {
    LinkFacebookAccountView()
        .environmentObject(RootRouter())
}
