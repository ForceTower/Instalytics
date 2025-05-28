package dev.forcetower.instalytics.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.forcetower.instalytics.data.storage.database.entity.InstagramAccount

@Dao
interface InstagramAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: InstagramAccount)

    @Query("SELECT * FROM InstagramAccount")
    suspend fun get(): List<InstagramAccount>
}