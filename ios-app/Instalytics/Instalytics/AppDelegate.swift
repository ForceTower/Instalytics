//
//  AppDelegate.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 18/04/25.
//

import Foundation
import UIKit
import InstalyticsKit
import FacebookCore

class AppDelegate : NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        startKoin()
        
        ApplicationDelegate.shared.application(application, didFinishLaunchingWithOptions: launchOptions)
        
        return true
    }
}
