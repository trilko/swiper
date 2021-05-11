package com.homework.swiper.dependencies.modules

import android.content.Context
import androidx.room.Room
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.AppDatabase
import com.homework.swiper.data.FragmentRoomRepository
import com.homework.swiper.data.entities.FragmentEntity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.*
import javax.inject.Singleton

@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        context: Context
    ): AppDatabase {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
        runBlocking {
            withContext(Dispatchers.IO) {
                if(db.fragmentDao().getData() == null) {
                    db.fragmentDao().add(FragmentEntity(0, 1, 1))
                }
            }
        }
        return db
    }

    @Provides
    fun getFragmentRepository(
        database: AppDatabase
    ): FragmentRepository = FragmentRoomRepository(database)
}