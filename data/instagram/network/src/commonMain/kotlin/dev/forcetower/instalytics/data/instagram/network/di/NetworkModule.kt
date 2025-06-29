package dev.forcetower.instalytics.data.instagram.network.di

import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object NetworkModule {
    val module = module {
        single<Json> {
            Json {
                ignoreUnknownKeys = true
            }
        }

        single<HttpClient> {
            val ktorClient = HttpClient {
                install(ContentNegotiation) {
                    json(get<Json>())
                }
            }

            val database = get<InstalyticsDB>()

            ktorClient.plugin(HttpSend).intercept { request ->
                val token = database.facebookAccessToken.requireCurrent()
                if (token == null) {
                    throw IllegalStateException("not connected :(")
                }

                val next = request.apply {
                    url.parameters.append("access_token", token.accessToken)
                }

                execute(next)
            }

            ktorClient
        }
    }
}