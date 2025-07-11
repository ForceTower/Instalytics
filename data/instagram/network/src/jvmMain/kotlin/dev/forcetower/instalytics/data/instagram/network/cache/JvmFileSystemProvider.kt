package dev.forcetower.instalytics.data.instagram.network.cache

import java.nio.file.Files
import java.nio.file.Paths
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toOkioPath

internal class JvmFileSystemProvider : FileSystemProvider {
    override val fileSystem: FileSystem
        get() = FileSystem.SYSTEM
    override val cacheDirectoryPath: Path?
        get() = Files.createDirectories(Paths.get("build/cache")).toOkioPath()
}
