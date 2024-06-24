package com.hivian.randomusers

import android.app.Application
import com.hivian.randomusers.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AppDelegate: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@AppDelegate)
        }
    }

}
