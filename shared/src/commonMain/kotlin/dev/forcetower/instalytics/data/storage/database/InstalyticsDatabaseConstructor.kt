package dev.forcetower.instalytics.data.storage.database

import androidx.room.RoomDatabaseConstructor

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "KotlinNoActualForExpect")
internal expect object InstalyticsDBConstructor : RoomDatabaseConstructor<InstalyticsDB> {
    override fun initialize(): InstalyticsDB
}