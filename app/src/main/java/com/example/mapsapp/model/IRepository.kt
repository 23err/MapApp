package com.example.mapsapp.model

import com.example.mapsapp.domain.Marker
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun saveMarker(marker: Marker)
    fun getMarkers(): Flow<List<Marker>>
    suspend fun updateMarker(marker: Marker)
    suspend fun getMarker(id: Long): Marker?
}