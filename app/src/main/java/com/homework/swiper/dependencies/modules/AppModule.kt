package com.homework.swiper.dependencies.modules

import android.app.Application
import android.content.Context
import com.homework.swiper.presentation.App
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

}