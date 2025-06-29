package dev.forcetower.instalytics.data.storage.database

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.instalytics.data.model.entity.FacebookAccessToken
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import dev.forcetower.instalytics.data.model.entity.InstagramMediaChild
import dev.forcetower.instalytics.data.storage.database.dao.FacebookAccessTokenDao
import dev.forcetower.instalytics.data.storage.database.dao.InstagramAccountDao
import dev.forcetower.instalytics.data.storage.database.dao.InstagramMediaChildDao
import dev.forcetower.instalytics.data.storage.database.dao.InstagramMediaDao

@Database(
    entities = [
        FacebookAccessToken::class,
        InstagramAccount::class,
        InstagramMedia::class,
        InstagramMediaChild::class,
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
    ]
)
@ConstructedBy(InstalyticsDatabaseConstructor::class)
abstract class InstalyticsDB : RoomDatabase() {
    abstract val facebookAccessToken: FacebookAccessTokenDao
    abstract val instagramAccount: InstagramAccountDao
    abstract val instagramMedia: InstagramMediaDao
    abstract val instagramMediaChildren: InstagramMediaChildDao
}