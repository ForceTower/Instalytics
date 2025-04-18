//
//  ContentView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 17/04/25.
//

import SwiftUI
import InstalyticsKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(Greeting().greet())
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
