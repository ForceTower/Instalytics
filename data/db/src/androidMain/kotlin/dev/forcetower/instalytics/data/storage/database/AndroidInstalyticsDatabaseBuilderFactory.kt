package dev.forcetower.instalytics.data.storage.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

internal class AndroidInstalyticsDatabaseBuilderFactory(private val context: Context) :
    InstalyticsDatabaseBuilderFactory {
    override fun create(): RoomDatabase.Builder<InstalyticsDB> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath("instalytics.db")
        return Room.databaseBuilder<InstalyticsDB>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}
