package dev.forcetower.instalytics.data.storage.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.forcetower.instalytics.data.model.composite.InstagramMediaWithChildren
import dev.forcetower.instalytics.data.model.entity.InstagramMedia
import kotlinx.coroutines.flow.Flow

@Dao
interface InstagramMediaDao : BaseDao<InstagramMedia> {
    @Transaction
    @Query(
        "SELECT * FROM InstagramMedia WHERE owner = (SELECT id FROM InstagramAccount WHERE me = 1 LIMIT 1)"
    )
    fun mine(): Flow<List<InstagramMediaWithChildren>>

    @Transaction
    @Query(
        "SELECT * FROM InstagramMedia WHERE owner = (SELECT id FROM InstagramAccount WHERE me = 1 LIMIT 1) ORDER BY timestamp DESC"
    )
    fun minePaged(): PagingSource<Int, InstagramMediaWithChildren>

    @Query("DELETE FROM InstagramMedia")
    suspend fun destroy()
}
