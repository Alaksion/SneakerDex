package com.alaksion.sneakerdex

import android.app.Application
import com.alaksion.sneakerdex.di.sneakerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SneakerDexApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SneakerDexApplication)
            modules(arrayListOf(sneakerModule))
        }
    }
}