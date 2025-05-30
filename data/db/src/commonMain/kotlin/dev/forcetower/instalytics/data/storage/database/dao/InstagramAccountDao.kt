package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import dev.forcetower.instalytics.data.model.entity.InstagramAccount

@Dao
abstract class InstagramAccountDao : BaseDao<InstagramAccount>() {
}