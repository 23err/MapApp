package com.example.mapsapp.model

import com.example.mapsapp.data.room.DatabaseFactory
import com.example.mapsapp.data.room.MarkersDB
import com.example.mapsapp.data.room.dao.MarkersDAO
import com.example.mapsapp.data.room.entity.RoomMarker
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.tool.mapper.MarkerMapper
import kotlinx.coroutines.flow.map


class Repository(
    private val db: MarkersDAO = DatabaseFactory.getDB().getMarkersDAO(),
    private val markerMapper: MarkerMapper = MarkerMapper()
) : IRepository {
    override suspend fun saveMarker(marker: Marker) {
        db.insertMarker(markerMapper.toRoomMarker(marker))
    }

    override fun getMarkers() = db.getMarkers().map { list ->
        list.map { roomMarker ->
            markerMapper.toMarker(roomMarker)
        }
    }

    override suspend fun updateMarker(marker: Marker) {
        db.updateMarker(markerMapper.toRoomMarker(marker))
    }

    override suspend fun getMarker(id: Long): Marker? = db.getMarker(id)?.let { roomMarker ->
        markerMapper.toMarker(roomMarker)
    }
}