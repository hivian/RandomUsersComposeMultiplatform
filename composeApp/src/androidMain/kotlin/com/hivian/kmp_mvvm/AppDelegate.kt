package com.hivian.kmp_mvvm

import android.app.Application
import com.hivian.kmp_mvvm.di.initKoin
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
