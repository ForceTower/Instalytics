package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.data.di.DatabaseComponent
import dev.forcetower.instalytics.data.instagram.profile.di.InstagramProfileDataBinds
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB

internal interface SharedApplicationComponent :
    DatabaseComponent,
    InstagramProfileDataBinds {
    val database: InstalyticsDB
}