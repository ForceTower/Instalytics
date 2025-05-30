package dev.forcetower.instalytics.data.instagram.profile.di

import de.jensklingenberg.ktorfit.Ktorfit
import dev.forcetower.instalytics.core.base.inject.ApplicationScope
import dev.forcetower.instalytics.data.instagram.profile.network.ProfileService
import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepository
import dev.forcetower.instalytics.data.instagram.profile.repository.InstagramProfileRepositoryImpl
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Provides

interface InstagramProfileDataBinds {
    val InstagramProfileRepositoryImpl.bind: InstagramProfileRepository
        @ApplicationScope @Provides
        get() = this

    @ApplicationScope
    @Provides
    fun provideService(
        database: InstalyticsDB
    ): ProfileService {
        val jsonInst = Json {
            ignoreUnknownKeys = true
        }

        val ktorClient = HttpClient {
            install(ContentNegotiation) {
                json(jsonInst)
            }
        }

        ktorClient.plugin(HttpSend).intercept { request ->
            val token = database.facebookAccessToken.requireCurrent()
            if (token == null) {
                throw IllegalStateException("Not authenticated")
            }

            val next = request.apply {
                url.parameters.append("access_token", token.accessToken)
            }

            execute(next)
        }

        return Ktorfit.Builder()
            .httpClient(ktorClient)
            .baseUrl("https://graph.facebook.com/v22.0/")
            .build()
            .create<ProfileService>()
    }
}