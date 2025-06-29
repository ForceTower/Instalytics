package dev.forcetower.instalytics.data.instagram.network.cache

import org.koin.core.module.Module
import org.koin.dsl.module

internal actual object CacheModule {
    actual val module: Module
        get() = module {
            single<FileSystemProvider> {
                AndroidFileSystemProvider(get())
            }
        }
}
