package com.homework.swiper.dependencies

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.homework.swiper.data.AppDatabase
import com.homework.swiper.dependencies.modules.*
import com.homework.swiper.presentation.application.App
import com.homework.swiper.presentation.base.BaseViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun getContext(): Context
    fun getViewModelFactory(): ViewModelProvider.Factory

    fun inject(db: AppDatabase)
    fun inject(baseViewModel: BaseViewModel)

}