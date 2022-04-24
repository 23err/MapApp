package com.example.mapsapp.model

import com.example.mapsapp.domain.Marker

interface IRepository {
    fun saveMarker(marker: Marker)
    fun getMarkers(): List<Marker>
    fun updateMarker(marker: Marker)
    fun deleteMarker(marker: Marker)
}