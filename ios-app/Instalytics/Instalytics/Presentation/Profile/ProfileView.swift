//
//  ProfileView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import InstalyticsKit
import SwiftUI
import SDWebImageSwiftUI

let images = [
    "https://images.unsplash.com/photo-1747582300720-9c71ee7f7fc2?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748096728390-0c0f12834975?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1746980372130-27b32e22b5ad?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747077138172-590c023ec442?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748100377329-429f657842de?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747582300720-9c71ee7f7fc2?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748096728390-0c0f12834975?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1746980372130-27b32e22b5ad?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747077138172-590c023ec442?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748100377329-429f657842de?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747582300720-9c71ee7f7fc2?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748096728390-0c0f12834975?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1746980372130-27b32e22b5ad?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747077138172-590c023ec442?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748100377329-429f657842de?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747582300720-9c71ee7f7fc2?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748096728390-0c0f12834975?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1746980372130-27b32e22b5ad?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1747077138172-590c023ec442?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1748100377329-429f657842de?q=80&w=1280&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
]

struct ProfileView: View {
    @ObservedObject var vm: ProfileViewModel = .init()
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack(spacing: 0) {
                ProfileHeader(account: vm.account)
                Spacer(minLength: 8)
                LazyVGrid(columns: Array(repeating: GridItem(.flexible(), spacing: 1), count: 3), spacing: 1) {
                    ForEach(vm.photos.indices, id: \.self) { index in
                        if let item = vm.getElement(index: Int32(index)) {
                            let string = item.imageUrl
                            GeometryReader { geometry in
                                let size = geometry.size.width
                                WebImage(url: URL(string: string)) { image in
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
                            .aspectRatio(1, contentMode: .fit)
                        }
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
    
    var body: some View {
        VStack(alignment: .leading) {
            WebImage(url: URL(string: account?.profilePictureUrl ?? "https://images.unsplash.com/photo-1606335192038-f5a05f761b3a?ixlib=rb-4.1.0&q=85&fm=jpg&crop=entropy&cs=srgb&dl=dillon-kydd-J6MK67MIs0I-unsplash.jpg&w=640")) { image in
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
            .padding(.trailing, 8)
            
            Text(account?.name ?? "...")
                .font(.title2)
            
            Text(account?.biography ?? "...")
                .font(.body)
                .foregroundStyle(.secondary)
            
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
            
            HStack {
                SmallProfileStat(text: account?.mediaCount.toInstagramString() ?? "", description: "Posts")
                SmallProfileStat(text: account?.follows.toInstagramString() ?? "", description: "Following")
                SmallProfileStat(text: account?.followers.toInstagramString() ?? "", description: "Followers")
            }
        }
        .padding(.horizontal)
        .frame(maxWidth: .infinity, alignment: .leading)
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
