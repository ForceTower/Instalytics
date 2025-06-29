package dev.forcetower.instalytics.domain.di

import dev.forcetower.instalytics.data.di.DatabaseModule
import dev.forcetower.instalytics.data.instagram.network.di.NetworkModule
import dev.forcetower.instalytics.data.instagram.profile.di.ProfileModule

internal object DomainAggregatorModule {
    val modules = buildList {
        addAll(DatabaseModule.modules)
        addAll(NetworkModule.modules)
        add(ProfileModule.module)
    }
}
