package dev.forcetower.instalytics.android

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import dev.forcetower.instalytics.di.create
import dev.forcetower.kmm.toolkit.logdog.LogdogLogger

class InstalyticsApplication : Application() {
    val component by lazy { AndroidApplicationComponent.create() }

    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
        LogdogLogger.install(AndroidLogdogLogger())
    }

    private fun setupStrictMode() {
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build(),
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectActivityLeaks()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectFileUriExposure()
                .detectCleartextNetwork()
                .detectContentUriWithoutPermission()
                .apply {
                    if (Build.VERSION.SDK_INT >= 29) {
                        detectCredentialProtectedWhileLocked()
                    }
                    if (Build.VERSION.SDK_INT >= 31) {
                        detectIncorrectContextUse()
                        detectUnsafeIntentLaunch()
                    }
                }
                .penaltyLog()
                .build(),
        )
    }
}