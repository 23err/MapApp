package com.example.mapsapp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.mapsapp.R
import com.example.mapsapp.databinding.FragmentMapBinding
import com.example.mapsapp.domain.Marker
import com.example.mapsapp.viewmodel.MapViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : BaseFragment<FragmentMapBinding>(FragmentMapBinding::inflate), OnMapReadyCallback {

     private lateinit var map: GoogleMap
    private val viewModel: MapViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapInit()
    }

    private fun mapInit() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        enableMyLocation()
        mapSetClickListeners()
        viewModel.markers.observe(viewLifecycleOwner) { setMarkers(it) }

    }

    private fun setMarkers(list: List<Marker>) {
        list.forEach {
            setMarker(it)
        }
    }

    private fun setMarker(marker: Marker) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(marker.lat, marker.lng))
                .title(marker.title)
        )
    }

    private fun mapSetClickListeners() {
        map.setOnMapLongClickListener {
            viewModel.newMarker(it.latitude, it.longitude)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            mapLocationEnable()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        checkLocationPermission(permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun checkLocationPermission(
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        for ((index, item) in permissions.withIndex()) {
            if (item == Manifest.permission.ACCESS_FINE_LOCATION) {
                if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    mapLocationEnable()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun mapLocationEnable() {
        map.isMyLocationEnabled = true
        map.uiSettings.apply {
            isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}