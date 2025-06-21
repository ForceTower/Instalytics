//
//  InsightsView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 21/06/25.
//

import SwiftUI

struct InsightsView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                Text("Overview")
                    .font(.title2)
                    .fontWeight(.medium)
                    .padding(.horizontal)
                
                LazyVGrid(columns: Array(repeating: GridItem(.flexible(minimum: 150)), count: 2), spacing: 8) {
                    InsightBigCard(title: "Followers", value: "1.2M", increase: 10)
                    InsightBigCard(title: "Accounts Reached", value: "235k", increase: 11)
                    InsightBigCard(title: "Content Interactions", value: "792k", increase: 20)
                    InsightBigCard(title: "Comments", value: "1k", increase: -15)
                    InsightBigCard(title: "Total Impressions", value: "11k", increase: -5)
                }
                .padding(.horizontal)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .toolbarTitleDisplayMode(.inline)
        .navigationTitle(Text("Insights"))
    }
}

struct InsightBigCard: View {
    let title: AttributedString
    let value: AttributedString
    let increase: Int
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.body)
            
            Text(value)
                .font(.title)
                .fontWeight(.bold)
            
            Text(increaseString())
                .font(.body)
                .foregroundColor(color(for: increase))
        }
        .padding(8)
        .frame(maxWidth: .infinity, alignment: .leading)
        .overlay {
            RoundedRectangle(cornerRadius: 8)
                .stroke(.secondary.opacity(0.5), lineWidth: 0.5)
        }
    }
    
    func increaseString() -> String {
        return if increase < 0 {
            "\(increase)%"
        } else {
            "+\(increase)%"
        }
    }
    
    func color(for increase: Int) -> Color {
        if increase > 0 {
            return .green
        } else if increase < 0 {
            return .red
        } else {
            return .secondary
        }
    }
}

#Preview {
    InsightsView()
}
