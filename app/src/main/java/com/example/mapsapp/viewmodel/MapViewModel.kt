package com.example.mapsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.model.IRepository
import com.example.mapsapp.model.Repository

class MapViewModel : ViewModel() {
    private val repository: IRepository = Repository()
    private val markersLiveData = MutableLiveData<List<Marker>>()
    val markers : LiveData<List<Marker>> = markersLiveData

    init {
        getMarkers()
    }

    fun newMarker(lat: Double, lng: Double) {
        val marker = Marker(lat, lng)
        repository.saveMarker(marker)
        getMarkers()
    }

    private fun getMarkers() {
        markersLiveData.postValue(repository.getMarkers())
    }
}

