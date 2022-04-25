package com.example.mapsapp.tool.mapper

import com.example.mapsapp.data.room.entity.RoomMarker
import com.example.mapsapp.domain.Marker

class MarkerMapper {
    fun toRoomMarker(marker: Marker) = RoomMarker(
        marker.id,
        marker.lat,
        marker.lng,
        marker.title,
        marker.annotation
    )

    fun toMarker(marker: RoomMarker) = Marker(
        marker.lat,
        marker.lng,
        marker.id,
        marker.title,
        marker.annotation
    )
}