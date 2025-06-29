package dev.forcetower.instalytics.data.instagram.network.di

import co.touchlab.kermit.Logger
import dev.forcetower.instalytics.data.instagram.network.cache.CacheModule
import dev.forcetower.instalytics.data.instagram.network.cache.FileSystemProvider
import dev.forcetower.instalytics.data.instagram.network.cache.InternalCacheStorage
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger as KtorLogger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object NetworkModule {
    private val module = module {
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
                install(Logging) {
                    logger = object : KtorLogger {
                        override fun log(message: String) {
                            Logger.d { message }
                        }
                    }
                    level = LogLevel.ALL
                }
                install(HttpCache) {
                    publicStorage(
                        InternalCacheStorage(
                            10 * 1024 * 1024, // 10MB
                            get<FileSystemProvider>()
                        )
                    )
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

    val modules = listOf(module, CacheModule.module)
}
