//
//  RootRouter.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 28/05/25.
//

import Foundation

enum RootState : Hashable {
    case initializing, login, facebook, connected
}

class RootRouter : ObservableObject {
    @Published var state: RootState = .connected
}
