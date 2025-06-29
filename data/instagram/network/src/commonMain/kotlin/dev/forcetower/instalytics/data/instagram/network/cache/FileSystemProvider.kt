package dev.forcetower.instalytics.data.instagram.network.cache

import okio.FileSystem
import okio.Path

internal interface FileSystemProvider {
    val fileSystem: FileSystem
    val cacheDirectoryPath: Path?
}