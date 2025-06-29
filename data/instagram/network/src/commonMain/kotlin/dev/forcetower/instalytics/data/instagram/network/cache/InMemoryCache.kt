package dev.forcetower.instalytics.data.instagram.network.cache

import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.http.Url
import io.ktor.util.collections.ConcurrentMap
import io.ktor.util.collections.ConcurrentSet

internal class InMemoryCache : CacheStorage {
    private val store by lazy { ConcurrentMap<Url, MutableSet<CachedResponseData>>() }

    override suspend fun store(url: Url, data: CachedResponseData) {
        val cache = store.computeIfAbsent(url) { ConcurrentSet() }
        if (!cache.add(data)) {
            cache.remove(data)
            cache.add(data)
        }
    }

    override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
        val data = store.getOrElse(key = url, defaultValue = { null })
        return data?.find {
            varyKeys.all { (key, value) -> it.varyKeys[key] == value }
        }
    }

    override suspend fun findAll(url: Url): Set<CachedResponseData> =
        store.getOrElse(key = url, defaultValue = {
            emptySet()
        })
}
