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
    
    init() {
        print(IosApplicationComponent.companion.create(someValue: 3).thing.run())
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
