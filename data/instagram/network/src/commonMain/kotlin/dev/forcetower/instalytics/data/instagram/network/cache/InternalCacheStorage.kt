package dev.forcetower.instalytics.data.instagram.network.cache

import co.touchlab.kermit.Logger
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.SYSTEM

internal class InternalCacheStorage(
    private val persistentCache: CacheStorage? = null,
    private val inMemoryCache: CacheStorage
) : CacheStorage {

    companion object Companion {

        private const val TAG = "InternalCacheStorage"

        internal fun createPersistentCache(
            fileSystem: FileSystem,
            directoryPath: Path?,
            maxSize: Long,
            dispatcher: CoroutineDispatcher
        ): PersistentCache? {
            if (directoryPath == null) return null
            return PersistentCache(
                fileSystem, directoryPath, maxSize, dispatcher
            )
        }
    }

    constructor(maxSize: Long, provider: FileSystemProvider) : this(
        persistentCache = createPersistentCache(
            fileSystem = provider.fileSystem,
            directoryPath = provider.cacheDirectoryPath,
            maxSize = maxSize,
            dispatcher = Dispatchers.IO
        ),
        inMemoryCache = InMemoryCache()
    )

    constructor(directoryPath: String, maxSize: Long) : this(
        persistentCache = createPersistentCache(
            fileSystem = FileSystem.SYSTEM,
            directoryPath = directoryPath.toPath(),
            maxSize = maxSize,
            dispatcher = Dispatchers.IO
        ),
        inMemoryCache = InMemoryCache()
    )

    override suspend fun store(url: Url, data: CachedResponseData) {
        try {
            if (persistentCache != null) {
                persistentCache.store(url, data)
            } else {
                logInMemoryUsage("error creating persistent cache.")
                inMemoryCache.store(url, data)
            }
        } catch (exception: Exception) {
            logInMemoryUsage(exception.message ?: "error storing response in persistence storage")
            inMemoryCache.store(url, data)
        }
    }

    override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
        return try {
            if (persistentCache != null) {
                persistentCache.find(url, varyKeys)
            } else {
                logInMemoryUsage("error creating persistent cache.")
                inMemoryCache.find(url, varyKeys)
            }
        } catch (exception: Exception) {
            logInMemoryUsage(exception.message ?: "error finding response from persistence storage")
            inMemoryCache.find(url, varyKeys)
        }
    }

    override suspend fun findAll(url: Url): Set<CachedResponseData> {
        return try {
            persistentCache?.findAll(url) ?: kotlin.run {
                logInMemoryUsage("error creating persistent cache.")
                inMemoryCache.findAll(url)
            }
        } catch (exception: Exception) {
            logInMemoryUsage(exception.message ?: "error finding response from persistence storage")
            inMemoryCache.findAll(url)
        }
    }

    private fun logInMemoryUsage(message: String) {
        Logger.d(TAG) { "Using in-memory cache, $message" }
    }
}