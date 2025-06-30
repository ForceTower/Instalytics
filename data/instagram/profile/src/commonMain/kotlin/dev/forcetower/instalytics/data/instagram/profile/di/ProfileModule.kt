package dev.forcetower.instalytics.data.instagram.profile.di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.forcetower.instalytics.data.instagram.profile.network.ProfileService
import dev.forcetower.instalytics.data.instagram.profile.network.createProfileService
import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepository
import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepositoryImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

object ProfileModule {
    val module = module {
        single<ProfileService> {
            Ktorfit.Builder()
                .httpClient(get<HttpClient>())
                .baseUrl("https://graph.facebook.com/v23.0/")
                .build()
                .createProfileService()
        }

        single<InstagramProfileRepository> {
            InstagramProfileRepositoryImpl(get(), get())
        }
    }
}
