package com.ikiugu.kitty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Alfred Ikiugu on 29/08/2021
 */

@HiltAndroidApp
class KittyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}