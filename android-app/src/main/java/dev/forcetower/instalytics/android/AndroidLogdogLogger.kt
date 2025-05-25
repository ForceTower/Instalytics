package dev.forcetower.instalytics.android

import android.util.Log
import dev.forcetower.kmm.toolkit.logdog.LogPriority
import dev.forcetower.kmm.toolkit.logdog.LogdogLogger

class AndroidLogdogLogger : LogdogLogger {
    override fun log(
        priority: LogPriority,
        tag: String,
        message: String
    ) {
        val prio = when (priority) {
            LogPriority.VERBOSE -> Log.VERBOSE
            LogPriority.DEBUG -> Log.DEBUG
            LogPriority.INFO -> Log.INFO
            LogPriority.WARN -> Log.WARN
            LogPriority.ERROR -> Log.ERROR
            LogPriority.ASSERT -> Log.ASSERT
        }
        Log.println(prio, tag, message)
    }

}