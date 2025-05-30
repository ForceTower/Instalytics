package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.forcetower.instalytics.data.model.entity.FacebookAccessToken

@Dao
abstract class FacebookAccessTokenDao : BaseDao<FacebookAccessToken>() {
    @Query("SELECT * FROM FacebookAccessToken LIMIT 1")
    abstract suspend fun requireCurrent(): FacebookAccessToken?

    @Query("UPDATE FacebookAccessToken SET active = 1 WHERE id = :id")
    protected abstract suspend fun markActive(id: String)

    @Query("UPDATE FacebookAccessToken SET active = 0")
    protected abstract suspend fun markAllInactive()

    @Transaction
    open suspend fun activate(id: String) {
        markAllInactive()
        markActive(id)
    }
}