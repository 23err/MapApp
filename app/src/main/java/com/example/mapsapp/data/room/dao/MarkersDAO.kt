package com.example.mapsapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.mapsapp.data.room.entity.RoomMarker
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkersDAO {
    @Query("SELECT * FROM markers")
    fun getMarkers(): Flow<List<RoomMarker>>

    @Insert(onConflict = REPLACE)
    suspend fun insertMarker(marker: RoomMarker)

    @Update(onConflict = REPLACE)
    suspend fun updateMarker(marker: RoomMarker)

    @Query("SELECT * FROM markers WHERE id=:id")
    suspend fun getMarker(id: Long): RoomMarker?
}