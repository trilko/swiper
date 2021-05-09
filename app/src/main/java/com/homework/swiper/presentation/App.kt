package com.homework.swiper.presentation

import android.app.Application
import com.homework.swiper.dependencies.AppComponent
import com.homework.swiper.dependencies.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().application(this).build()
    }

}