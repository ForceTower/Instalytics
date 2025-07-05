//
//  InsightsView.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 21/06/25.
//

import SwiftUI
import Charts
import SDWebImageSwiftUI

struct InsightsView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 0) {
                HStack(spacing: 0) {
                    VStack(alignment: .leading, spacing: 0) {
                        Text("Overview")
                            .font(.title)
                            .fontWeight(.bold)
                            .padding(.horizontal)
                        
                        Text("Jun 30 - Jul 6")
                            .font(.headline)
                            .fontWeight(.medium)
                            .padding(.horizontal)
                            .padding(.top, 6)
                        
                        Text("vs Jun 29 - Jul 23")
                            .font(.footnote)
                            .fontWeight(.light)
                            .foregroundStyle(.secondary)
                            .padding(.horizontal)
                            .padding(.top, 4)
                    }
                    
                    Spacer()
                    
                    WebImage(url: URL(string: "https://images.unsplash.com/photo-1751132901281-82f7fbda1786?q=80&w=1450&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")) { image in
                        image
                            .resizable()
                            .scaledToFill()
                    } placeholder: {
                        Rectangle().foregroundColor(.gray)
                    }
                    .indicator(.activity)
                    .transition(.fade(duration: 0.5))
                    .frame(width: 64, height: 64)
                    .clipShape(.circle)
                    .padding(.horizontal)
                }
                
                Divider()
                    .padding(.vertical, 16)
                
                Text("Engagement")
                    .font(.body)
                    .foregroundStyle(.secondary)
                    .padding(.horizontal)
                
                Text("3,245")
                    .font(.largeTitle)
                    .fontWeight(.medium)
                    .padding(.horizontal)
                    .padding(.top, 1)
                
                Label {
                    Text("+13.45%")
                        .foregroundColor(.green)
                } icon: {
                    Image(systemName: "arrow.up.circle.fill")
                        .foregroundColor(.green)
                }
                .font(.subheadline)
                .padding(.top, 4)
                .padding(.horizontal)

                
                EngagementChart()
                    .padding(.top, 16)
                
                Divider()
                    .padding(.top, 24)
                    .padding(.bottom, 16)
                
                LazyVGrid(columns: Array(repeating: GridItem(.flexible(minimum: 150)), count: 2), spacing: 8) {
                    InsightBigCard(title: "Followers", value: "1.2M", increase: 10)
                    InsightBigCard(title: "Accounts Reached", value: "235k", increase: 11)
                    InsightBigCard(title: "Content Interactions", value: "792k", increase: 20)
                    InsightBigCard(title: "Comments", value: "1k", increase: -15)
                    InsightBigCard(title: "Total Impressions", value: "11k", increase: -5)
                }
                .padding(.top, 8)
                .padding(.horizontal)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .toolbarTitleDisplayMode(.inline)
        .navigationTitle(Text("Insights"))
    }
}

struct DailyData: Identifiable {
    let id = UUID()
    let dayIndex: Int
    let day: String
    let value: Int
}

struct EngagementChart: View {
    let data: [DailyData] = [
        .init(dayIndex: 1, day: "Mon", value: 420),
        .init(dayIndex: 2, day: "Tue", value: 510),
        .init(dayIndex: 3, day: "Wed", value: 650),
        .init(dayIndex: 4, day: "Thu", value: 580),
        .init(dayIndex: 5, day: "Fri", value: 630),
        .init(dayIndex: 6, day: "Sat", value: 300),
        .init(dayIndex: 7, day: "Sun", value: 155)
    ]
    
    let lastWeek: [DailyData] = [
        .init(dayIndex: 1, day: "Mon", value: 360),
        .init(dayIndex: 2, day: "Tue", value: 450),
        .init(dayIndex: 3, day: "Wed", value: 520),
        .init(dayIndex: 4, day: "Thu", value: 480),
        .init(dayIndex: 5, day: "Fri", value: 550),
        .init(dayIndex: 6, day: "Sat", value: 270),
        .init(dayIndex: 7, day: "Sun", value: 230)
    ]
    
    var body: some View {
        Chart {
            ForEach(data) { item in
                LineMark(x: .value("Day", item.dayIndex), y: .value("Value", item.value), series: .value("week", "A"))
                    .interpolationMethod(.monotone)
                
                AreaMark(x: .value("Day", item.dayIndex), y: .value("Value", item.value), series: .value("week", "A"))
                    .interpolationMethod(.monotone)
                    .foregroundStyle(
                        LinearGradient(colors: [.accentColor.opacity(0.6), .accentColor.opacity(0.1)], startPoint: .top, endPoint: .bottom)
                    )
            }
            
            ForEach(lastWeek) { item in
                LineMark(x: .value("Day", item.dayIndex), y: .value("Value", item.value), series: .value("week", "B"))
                    .interpolationMethod(.monotone)
                    .lineStyle(.init(lineWidth: 0.5, dash: [8, 4]))
                    .foregroundStyle(.secondary)
            }
            
        }
        .chartYAxis {
            AxisMarks(position: .leading)
        }
        .chartXScale(domain: 1...7)
        .chartXAxis {
            AxisMarks(values: Array(1...7)) { value in
                AxisTick()
                AxisValueLabel(centered: true) {
                    Text(data[Int(value.as(Double.self) ?? 1) - 1].day)
                }
            }
        }
        .chartForegroundStyleScale(type: .linear)
        .padding(.horizontal)
        .frame(height: 200)
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
                .foregroundStyle(.secondary)
            
            Text(value)
                .font(.title)
                .fontWeight(.bold)
            
            Label {
                Text("\(increase)%")
            } icon: {
                if increase < 0 {
                    Image(systemName: "arrow.down.circle.fill")
                } else {
                    Image(systemName: "arrow.up.circle.fill")
                }
            }
            .foregroundStyle(color(for: increase))
            .font(.body)
        }
        .padding(.vertical, 8)
        .frame(maxWidth: .infinity, alignment: .leading)
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
