package dev.forcetower.instalytics.data.storage.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.forcetower.instalytics.data.model.entity.FacebookAccessToken
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import dev.forcetower.instalytics.data.storage.database.dao.FacebookAccessTokenDao
import dev.forcetower.instalytics.data.storage.database.dao.InstagramAccountDao

@Database(
    entities = [
        FacebookAccessToken::class,
        InstagramAccount::class,
    ],
    version = 1
)
@ConstructedBy(InstalyticsDatabaseConstructor::class)
abstract class InstalyticsDB : RoomDatabase() {
    abstract val facebookAccessToken: FacebookAccessTokenDao
    abstract val instagramAccount: InstagramAccountDao
}