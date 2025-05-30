package dev.forcetower.instalytics.data.storage.database

import androidx.room.RoomDatabase

interface InstalyticsDatabaseBuilderFactory {
    fun create(): RoomDatabase.Builder<InstalyticsDB>
}