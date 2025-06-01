//
//  InstalyticsApp.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 17/04/25.
//

import SwiftUI
import InstalyticsKit

@main
struct InstalyticsApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    @ObservedObject var router = RootRouter()
    
    var body: some Scene {
        WindowGroup {
            RootView()
                .environmentObject(router)
        }
    }
}
