package dev.forcetower.instalytics.android.ui.di

import dev.forcetower.instalytics.android.ui.navigation.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModel {
            ProfileViewModel(get())
        }
    }
}