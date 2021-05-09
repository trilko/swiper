package com.homework.swiper.dependencies.modules

import android.content.Context
import androidx.room.Room
import com.homework.swiper.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

}