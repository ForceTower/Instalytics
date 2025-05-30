package dev.forcetower.instalytics.data.di.storage.database

import androidx.room.Room
import androidx.room.RoomDatabase
import dev.forcetower.instalytics.data.storage.database.InstalyticsDB
import dev.forcetower.instalytics.data.storage.database.InstalyticsDatabaseBuilderFactory
import java.io.File

internal class JvmInstalyticsDatabaseBuilderFactory : InstalyticsDatabaseBuilderFactory {
    override fun create(): RoomDatabase.Builder<InstalyticsDB> {
        return Room.databaseBuilder<InstalyticsDB>(
            name = databaseFile.absolutePath,
        )
    }
}

private val databaseFile: File
    get() = File(appDir.also { if (!it.exists()) it.mkdirs() }, "instalytics.db")

private val appDir: File
    get() {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> {
                File(System.getenv("AppData"), "instalytics/db")
            }

            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                File(System.getProperty("user.home"), ".instalytics")
            }

            os.contains("mac") -> {
                File(System.getProperty("user.home"), "Library/Application Support/instalytics")
            }

            else -> error("Unsupported operating system")
        }
    }
