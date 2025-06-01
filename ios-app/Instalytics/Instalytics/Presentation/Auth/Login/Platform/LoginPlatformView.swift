//
//  LoginPlatformView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import SwiftUI

struct LoginPlatformView: View {
    @Binding var path: NavigationPath
    @State var email = ""
    
    var body: some View {
        ZStack {
            ScrollView {
                VStack {
                    Image(.illustrationWomanGraph)
                        .resizable()
                        .scaledToFit()
                        .frame(maxWidth: .infinity)
                        .ignoresSafeArea()
                    
                    VStack {
                        Text("Log in to your account")
                            .font(.title2)
                            .fontWeight(.bold)
                        
                        Text("Type your email to receive a login code")
                            .font(.caption)
                            .fontWeight(.regular)
                        
                        HStack {
                            Text("Email")
                                .font(.subheadline)
                                .fontWeight(.light)
                                .frame(width: 70, alignment: .leading)
                            Divider().frame(height: 15)
                            TextField("", text: $email)
                                .textFieldStyle(.automatic)
                                .textContentType(.emailAddress)
                                .autocorrectionDisabled()
                                .textInputAutocapitalization(.never)
                                .submitLabel(.send)
                        }
                        .padding()
                        .background(.background.opacity(0.7))
                        .clipShape(.rect(cornerRadius: 8))
                        .padding(.horizontal)
                        
                        Button {
                            
                        } label: {
                            Text("Login")
                                .padding(.horizontal)
                        }
                        .buttonStyle(.borderedProminent)
                        .controlSize(.regular)
                        .padding(.top)
                    }
                    .frame(alignment: .top)
                }
                .frame(alignment: .top)
            }
            .frame(maxHeight: .infinity)
            .ignoresSafeArea()
        }
        .scrollDisabled(true)
    }
}

#Preview {
    @Previewable @State var path: NavigationPath = .init()
    LoginPlatformView(path: $path)
}
