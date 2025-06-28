package dev.forcetower.instalytics.android

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import co.touchlab.kermit.LogcatWriter
import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import dev.forcetower.instalytics.di.create

class InstalyticsApplication : Application() {
    val component by lazy { AndroidApplicationComponent.create(this) }

    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
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