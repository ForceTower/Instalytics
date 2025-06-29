package dev.forcetower.instalytics.data.instagram.network.cache

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

internal class DarwinFileSystemProvider : FileSystemProvider {
    private val manager by lazy { NSFileManager.defaultManager }

    override val fileSystem: FileSystem
        get() = FileSystem.SYSTEM

    override val cacheDirectoryPath: Path?
        get() = getDirPath(NSCachesDirectory, true)

    private fun getDirPath(directory: NSSearchPathDirectory, create: Boolean = false): Path? {
        val dirUrl = getDirUrl(directory, create)?.path
        return dirUrl?.toPath()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun getDirUrl(directory: NSSearchPathDirectory, create: Boolean = false): NSURL? {
        memScoped {
            val error = alloc<ObjCObjectVar<NSError?>>()
            return manager
                .URLForDirectory(
                    directory,
                    NSUserDomainMask,
                    null,
                    create,
                    error.ptr
                )?.standardizedURL
        }
    }
}
