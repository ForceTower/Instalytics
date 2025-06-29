package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instalytics.data.model.entity.InstagramAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface InstagramAccountDao : BaseDao<InstagramAccount> {
    @Query("SELECT * FROM InstagramAccount WHERE me = 1 LIMIT 1")
    suspend fun requireMe(): InstagramAccount?

    @Query("SELECT * FROM InstagramAccount WHERE me = 1 LIMIT 1")
    fun me(): Flow<InstagramAccount?>
}