package com.example.mvvmexample

import android.app.Application
import com.example.mvvmexample.dependency_injection.moviesModule
import com.example.mvvmexample.dependency_injection.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MainApplication)
            // use modules
            modules(moviesModule, networkModule)
        }
    }
}