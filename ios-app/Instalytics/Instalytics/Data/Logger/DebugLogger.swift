//
//  DebugLogger.swift
//  Instalytics
//
//  Created by João Paulo Santos Sena on 18/04/25.
//

import Foundation
import InstalyticsKit
import os

//class DebugLogger : LogdogLogdogLogger {
//    private let logger = Logger(subsystem: "dev.forcetower.instalytics", category: "logdog")
//    func isLoggable(priority: LogdogLogPriority) -> Bool {
//        return true
//    }
//    
//    func log(priority: LogdogLogPriority, tag: String, message: String) {
//        let mapped = switch priority {
//            case .verbose:
//                OSLogType.debug
//            case .info:
//                OSLogType.info
//            case .warn:
//                OSLogType.fault
//            case .error:
//                OSLogType.error
//            case .debug:
//                OSLogType.default
//            case .assert:
//                OSLogType.fault
//            default:
//                OSLogType.default
//        }
//        logger.log(level: mapped, "\(tag): \(message)")
//    }
//}
