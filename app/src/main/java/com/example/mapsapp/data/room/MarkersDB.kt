package com.example.mapsapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mapsapp.data.room.dao.MarkersDAO
import com.example.mapsapp.data.room.entity.RoomMarker

@Database(entities = arrayOf(RoomMarker::class), version = 1, exportSchema = false)
abstract class MarkersDB : RoomDatabase() {
    abstract fun getMarkersDAO(): MarkersDAO
}