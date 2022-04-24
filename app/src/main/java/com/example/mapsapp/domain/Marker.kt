package com.example.mapsapp.domain

data class Marker(
    val lat: Double,
    val lng: Double,
    val id: Long? = null,
    val title: String? = null,
    val annotation: String? = null
)
