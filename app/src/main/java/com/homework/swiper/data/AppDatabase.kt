package com.homework.swiper.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.homework.swiper.data.entities.*

@Database(entities = [FragmentEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fragmentDao(): FragmentDao
}