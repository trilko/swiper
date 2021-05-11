package com.homework.swiper.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.homework.swiper.data.entities.FragmentEntity

@Dao
interface FragmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(entity: FragmentEntity)

    @Query("SELECT * FROM fragmententity")
    fun getData(): FragmentEntity?

    @Update
    suspend fun update(entity: FragmentEntity)

    @Query("SELECT * FROM fragmententity WHERE id = 0")
    fun getFragmentEntity(): LiveData<FragmentEntity>

}