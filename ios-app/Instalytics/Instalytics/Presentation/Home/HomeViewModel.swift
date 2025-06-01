//
//  HomeViewModel.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 30/05/25.
//

import SwiftUI
import Combine

class HomeViewModel : ObservableObject {
    @Published var tabSelection: HomeTabSelection = .profile
}
