package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instalytics.data.model.entity.InstagramMediaChild
import kotlinx.coroutines.flow.Flow

@Dao
interface InstagramMediaChildDao : BaseDao<InstagramMediaChild> {
    @Query("SELECT * FROM InstagramMediaChild WHERE parentId = :mediaId")
    fun children(mediaId: String): Flow<List<InstagramMediaChild>>
}
