package com.example.mapsapp.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "markers")
data class RoomMarker (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val lat: Double,
    val lng: Double,
    val title: String? = null,
    val annotation: String? = null
)