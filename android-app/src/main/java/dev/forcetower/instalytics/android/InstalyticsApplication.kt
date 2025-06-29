package dev.forcetower.instalytics.android

import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import dev.forcetower.instalytics.android.di.ApplicationModules
import dev.forcetower.instalytics.android.ui.di.PresentationModule
import dev.forcetower.instalytics.di.AndroidApplicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InstalyticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupStrictMode()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            allowOverride(false)
            androidLogger()
            androidContext(this@InstalyticsApplication)
            modules(ApplicationModules.allModules)
        }
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