package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.di.DatabaseComponent
import dev.forcetower.instalytics.data.instagram.profile.di.InstagramProfileDataBinds
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import dev.forcetower.instalytics.domain.OtherTest
import dev.forcetower.instalytics.domain.ThingDomain
import me.tatarka.inject.annotations.Provides

internal interface SharedApplicationComponent :
    DatabaseComponent,
    InstagramProfileDataBinds {
    val thing: ThingDomain
    val database: InstalyticsDB

    @Provides
    @ApplicationScope
    fun provideElement(): OtherTest {
        return OtherTest("Hey there world!")
    }
}