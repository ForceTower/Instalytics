//
//  HomeView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import SwiftUI

struct HomeView: View {
    @State private var menuPath = NavigationPath()
    @StateObject private var vm: HomeViewModel = .init()
    
    var selectionBinding: Binding<HomeTabSelection> {
        Binding(
            get: { vm.tabSelection },
            set: { vm.tabSelection = $0 }
        )
    }
    
    var body: some View {
        TabView(selection: selectionBinding) {
            NavigationStack {
                InsightsView()
            }.tabItem {
                Label("Insights", systemImage: "star.circle.fill")
            }.tag(HomeTabSelection.insights)
            
            NavigationStack {
                ProfileView()
            }.tabItem {
                Label("Profile", systemImage: "person.crop.circle")
            }.tag(HomeTabSelection.profile)
        }
    }
}

#Preview {
    HomeView()
}
