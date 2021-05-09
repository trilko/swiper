package com.homework.swiper.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.homework.swiper.data.entities.ActualFragment
import com.homework.swiper.data.entities.Fragments

@Dao
interface FragmentDao {

    @Insert
    suspend fun add(fragment: Fragments)

    @Query("DELETE FROM fragments WHERE id >= :since")
    suspend fun remove(since: Int)

    @Query("SELECT COUNT(id) FROM Fragments")
    fun getAmount(): LiveData<Int>

    @Query("SELECT actualFragment FROM ActualFragment")
    fun getActualFragment(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActualFragment(actual: ActualFragment)

}