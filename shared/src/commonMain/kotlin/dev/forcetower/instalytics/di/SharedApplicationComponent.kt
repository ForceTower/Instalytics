package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.domain.OtherTest
import dev.forcetower.instalytics.domain.ThingDomain
import me.tatarka.inject.annotations.Provides

interface SharedApplicationComponent {
    val thing: ThingDomain

    @Provides
    @ApplicationScope
    fun provideElement(): OtherTest {
        return OtherTest("Hey there world!")
    }
}