package dev.forcetower.instalytics.di

import dev.forcetower.instalytics.domain.di.DomainExternalModule
import org.koin.core.module.Module

interface SharedApplicationComponent {
    val modules: List<Module>
        get() = DomainExternalModule.modules
}
