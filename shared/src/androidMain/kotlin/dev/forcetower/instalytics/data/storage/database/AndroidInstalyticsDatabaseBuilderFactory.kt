package dev.forcetower.instalytics.data.storage.database

import android.content.Context
import androidx.room.RoomDatabase

internal class AndroidInstalyticsDatabaseBuilderFactory(
    private val context: Context
) : InstalyticsDatabaseBuilderFactory {
    override fun create(): RoomDatabase.Builder<InstalyticsDB> {
        return getDatabaseBuilder(context)
    }
}