package dev.forcetower.instalytics.data.storage.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

internal fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<InstalyticsDB> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("instalytics.db")
    return Room.databaseBuilder<InstalyticsDB>(
        context = appContext,
        name = dbFile.absolutePath
    )
}