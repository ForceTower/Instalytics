package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.forcetower.instalytics.data.model.entity.InstagramCursor

@Dao
interface InstagramCursorDao : BaseDao<InstagramCursor> {
    @Query("SELECT * FROM InstagramCursor WHERE id = :id AND type = :type")
    suspend fun query(id: String, type: String): InstagramCursor?

    @Query("SELECT * FROM InstagramCursor WHERE id = :id AND type = :type AND OWNER = :owner")
    suspend fun query(id: String, type: String, owner: String): InstagramCursor?

    @Query("DELETE FROM InstagramCursor WHERE type = :type")
    suspend fun clearType(type: String)

    @Query("DELETE FROM InstagramCursor WHERE type = :type AND owner = :owner")
    suspend fun clearTypeOwner(type: String, owner: String)
}
