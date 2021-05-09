package com.homework.swiper.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.entities.ActualFragment
import com.homework.swiper.data.entities.Fragments

@Dao
interface FragmentDao: FragmentRepository {

    @Insert
    override suspend fun add(fragment: Fragments)

    @Delete
    override suspend fun remove(fragment: Fragments): Int

    @Query("SELECT COUNT(id) FROM Fragments")
    override fun getAmount(): LiveData<Int>

    @Query("SELECT actualFragment FROM ActualFragment WHERE id = 0")
    override fun getActualFragment(): LiveData<Int>

    @Update
    override suspend fun updateActualFragment(actual: ActualFragment): Int
}