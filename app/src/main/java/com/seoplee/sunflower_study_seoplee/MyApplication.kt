package com.seoplee.sunflower_study_seoplee

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    init {
        appContext = this
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}