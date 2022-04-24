package com.example.mapsapp.model

import com.example.mapsapp.domain.Marker


class Repository : IRepository {
    private val listOfMarkers = mutableListOf<Marker>()
    override fun saveMarker(marker: Marker) {
        listOfMarkers.add(marker)
    }

    override fun getMarkers(): List<Marker> = listOfMarkers

    override fun updateMarker(marker: Marker) {
        val index = listOfMarkers.indexOf(marker)
        listOfMarkers[index] = marker
    }

    override fun deleteMarker(marker: Marker) {
        listOfMarkers.remove(marker)
    }
}