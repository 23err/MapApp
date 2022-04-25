package com.example.mapsapp.tool

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.mapsapp.R
import com.example.mapsapp.view.MarkerFragment
import com.example.mapsapp.view.MarkersFragment

class Navigation(private val fragmentManager: FragmentManager) {
    fun toMarkers() {
        val fragment = MarkersFragment()
        navigateTo(fragment)
    }

    fun toMarker(id: Long) {
        val fragment = MarkerFragment.getInstance(id)
        navigateTo(fragment)
    }

    fun popBackStack() {
        fragmentManager.popBackStack()
    }

    private fun navigateTo(fragment: Fragment) {
        fragmentManager.commit {
            replace(R.id.container, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}