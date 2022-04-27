package com.example.mapsapp.data.room

import androidx.room.Room
import com.example.mapsapp.App

object DatabaseFactory {

    private const val DB_NAME = "Markers.db"
    private val db by lazy {
        Room.databaseBuilder(
            App.getContext(),
            MarkersDB::class.java,
            DB_NAME
        ).build()
    }

    fun getDB() = db
}