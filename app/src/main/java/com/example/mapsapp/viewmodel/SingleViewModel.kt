package com.example.mapsapp.viewmodel

import androidx.lifecycle.*
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.model.IRepository
import com.example.mapsapp.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SingleViewModel : ViewModel() {
    private val repository: IRepository = Repository()
    private val markersLiveData = MutableLiveData<List<Marker>>()
    private var currentMarker: Marker? = null
    val markers: LiveData<List<Marker>> = markersLiveData

    init {
        getMarkers()
    }

    fun newMarker(lat: Double, lng: Double) {
        val marker = Marker(lat, lng)
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveMarker(marker)
        }
    }

    private fun getMarkers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMarkers().collect { list ->
                markersLiveData.postValue(list)
            }
        }
    }

    fun getMarker(id: Long): LiveData<Marker> {
        val markerLiveData = MutableLiveData<Marker>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMarker(id)?.let { marker ->
                markerLiveData.postValue(marker)
                currentMarker = marker
            }
        }
        return markerLiveData
    }

    fun updateMarker(title: String, annotation: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentMarker?.let {
                val marker = it.copy(title = title, annotation = annotation)
                repository.updateMarker(marker)
            }
        }
    }
}

