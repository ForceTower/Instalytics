//
//  ProfileView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import InstalyticsKit
import SwiftUI
import SDWebImageSwiftUI

struct ProfileView: View {
    @StateObject private var vm: ProfileViewModel = .init()
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack(spacing: 0) {
                ProfileHeader(account: vm.account, photo: vm.photos.count > 0 ? vm.getElement(index: 0)?.imageUrl : nil)
                Spacer(minLength: 16)
                LazyVGrid(columns: Array(repeating: GridItem(.flexible(), spacing: 1), count: 3), spacing: 1) {
                    ForEach(vm.photos.indices, id: \.self) { index in
                        GeometryReader { geometry in
                            if let item = vm.getElement(index: Int32(index)) {
                                let size = geometry.size.width
                                WebImage(url: URL(string: item.imageUrl)) { image in
                                    image
                                        .resizable()
                                        .scaledToFill()
                                        .allowedDynamicRange(.constrainedHigh)
                                        .frame(width: size, height: size)
                                        .clipped()
                                } placeholder: {
                                    Rectangle().foregroundColor(.gray)
                                }
                            }
                        }
                        .aspectRatio(1, contentMode: .fit)
                    }
                }
            }
        }
        .toolbarTitleDisplayMode(.inline)
        .navigationTitle(Text("Profile"))
        .onAppear {
            vm.onAppear()
        }
    }
}

struct ProfileHeader: View {
    let account: InstagramAccountUI?
    let photo: String?
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Spacer()
                
                VStack {
                    Text(account?.follows.toInstagramString() ?? "...")
                        .font(.title3)
                        .fontWeight(.medium)
                    
                    Text("Following")
                        .font(.subheadline)
                }
                
                Spacer()
                
                WebImage(url: URL(string: account?.profilePictureUrl ?? "")) { image in
                    image
                        .resizable()
                        .scaledToFill()
                } placeholder: {
                    Rectangle().foregroundColor(.gray)
                }
                .indicator(.activity)
                .transition(.fade(duration: 0.5))
                .frame(width: 128, height: 128)
                .clipShape(.circle)
                
                Spacer()
                
                VStack {
                    Text(account?.followers.toInstagramString() ?? "...")
                        .font(.title3)
                        .fontWeight(.medium)
                    
                    Text("Followers")
                        .font(.subheadline)
                }
                
                Spacer()
            }
            
            Text(account?.name ?? "...")
                .font(.title3)
                .multilineTextAlignment(.leading)
                .frame(maxWidth: .infinity)
            
            Text(account?.biography ?? "...")
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .frame(maxWidth: .infinity)
            
            HStack {
                Button {
                    
                } label: {
                    Text("Open Profile")
                }
                .buttonStyle(.bordered)
                
                Button {
                    
                } label: {
                    Text("Share Profile")
                }
                .buttonStyle(.borderedProminent)
            }
            .frame(maxWidth: .infinity)
            .padding(.top)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .background {
            GeometryReader { geometry in
                Rectangle()
                    .foregroundStyle(
                        LinearGradient(colors: [Color(UIColor.systemBackground).opacity(1), Color(UIColor.systemBackground).opacity(0.5), Color(UIColor.systemBackground).opacity(1)],
                                       startPoint: .top,
                                       endPoint: .bottom)
                    )
                    .background {
                        if let photo = photo {
                            WebImage(url: URL(string: photo)) { image in
                                image
                                    .resizable()
                                    .frame(width: geometry.size.width, height: geometry.size.height)
                                    .scaledToFill()
                            } placeholder: {
                                Rectangle().foregroundColor(.clear)
                            }
                            .transition(.fade(duration: 0.5))
                            .blur(radius: 8)
                        }
                    }
            }
            .clipped()
            
        }
    }
}

struct SmallProfileStat: View {
    let text: String
    let description: AttributedString
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(text)
                .font(.title2)
                .fontWeight(.medium)
            
            Text(description)
                .font(.body)
                .foregroundStyle(.secondary)
        }
        .padding(8)
        .overlay {
            RoundedRectangle(cornerRadius: 8)
                .stroke(.secondary.opacity(0.5), lineWidth: 0.5)
        }
    }
}

#Preview {
    ProfileView()
}
