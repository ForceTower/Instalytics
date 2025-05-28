package dev.forcetower.instalytics.data.storage.database

import androidx.room.RoomDatabase

internal interface InstalyticsDatabaseBuilderFactory {
    fun create(): RoomDatabase.Builder<InstalyticsDB>
}