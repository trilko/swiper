package com.homework.swiper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.homework.swiper.data.entities.FragmentEntity

@Database(entities = [FragmentEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fragmentDao(): FragmentDao
}