package com.example.mapsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mapsapp.view.MapFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initContainter()
        }
    }

    private fun initContainter() {
        val mapFragment = MapFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, mapFragment)
            .setReorderingAllowed(true)
            .commitAllowingStateLoss()
    }
}