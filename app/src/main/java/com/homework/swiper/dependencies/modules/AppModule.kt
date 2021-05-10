package com.homework.swiper.dependencies.modules

import android.content.Context
import com.homework.swiper.presentation.application.App
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

}