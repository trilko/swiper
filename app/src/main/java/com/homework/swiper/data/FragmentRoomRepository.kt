package com.homework.swiper.data

import androidx.lifecycle.LiveData
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.utils.FragmentMapper
import javax.inject.Inject

class FragmentRoomRepository @Inject constructor(
    database: AppDatabase
): FragmentRepository {

    private val fragmentDao = database.fragmentDao()

    override val model: LiveData<FragmentModel> = FragmentMapper().mapToModel(fragmentDao.getFragmentEntity())

    override suspend fun set(model: FragmentModel) {
        fragmentDao.update(FragmentMapper().mapToEntity(model))
    }

}