package dev.forcetower.instalytics.log

import dev.forcetower.kmm.toolkit.logdog.LogdogLogger

fun install(logger: LogdogLogger) {
    LogdogLogger.install(logger)
}