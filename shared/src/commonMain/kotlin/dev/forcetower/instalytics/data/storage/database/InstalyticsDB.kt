package dev.forcetower.instalytics.data.storage.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.instalytics.data.storage.database.dao.InstagramAccountDao
import dev.forcetower.instalytics.data.storage.database.entity.InstagramAccount

@Database(
    entities = [
        InstagramAccount::class,
    ],
    version = 1
)
@ConstructedBy(InstalyticsDatabaseConstructor::class)
abstract class InstalyticsDB : RoomDatabase() {
    abstract val instagramAccounts: InstagramAccountDao
}