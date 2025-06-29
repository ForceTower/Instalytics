package dev.forcetower.instalytics.data.instagram.network.cache

import android.content.Context
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

internal class AndroidFileSystemProvider(
    private val context: Context
): FileSystemProvider {
    override val fileSystem: FileSystem
        get() = FileSystem.SYSTEM
    override val cacheDirectoryPath: Path?
        get() = context.cacheDir.absolutePath.toPath()
}