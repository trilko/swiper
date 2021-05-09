package com.homework.swiper.dependencies

import android.content.Context
import com.homework.swiper.data.AppDatabase
import com.homework.swiper.dependencies.modules.AppModule
import com.homework.swiper.dependencies.modules.DatabaseModule
import com.homework.swiper.presentation.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun getContext(): Context

    fun inject(db: AppDatabase)

}