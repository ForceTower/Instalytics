package dev.forcetower.instalytics.data.general.di

import dev.forcetower.instalytics.data.general.repository.InitialChecksRepository
import dev.forcetower.instalytics.data.general.repository.InitialChecksRepositoryImpl
import org.koin.dsl.module

object GeneralModule {
    val module = module {
        single<InitialChecksRepository> {
            InitialChecksRepositoryImpl(get())
        }
    }
}