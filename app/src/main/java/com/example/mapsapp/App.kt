package com.example.mapsapp

import android.app.Application

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun getContext() = instance!!.applicationContext
    }
}