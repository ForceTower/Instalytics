package dev.forcetower.instalytics.data.storage.database

import androidx.room.RoomDatabase

internal class DarwinInstalyticsDatabaseBuilderFactory : InstalyticsDatabaseBuilderFactory {
    override fun create(): RoomDatabase.Builder<InstalyticsDB> = getDatabaseBuilder()
}
