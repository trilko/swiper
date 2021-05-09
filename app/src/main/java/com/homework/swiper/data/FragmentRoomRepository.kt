package com.homework.swiper.data

import androidx.lifecycle.LiveData
import com.homework.swiper.FragmentRepository
import com.homework.swiper.data.entities.ActualFragment
import com.homework.swiper.data.entities.Fragments
import com.homework.swiper.data.models.FragmentModel
import javax.inject.Inject

class FragmentRoomRepository @Inject constructor(
    database: AppDatabase
): FragmentRepository {

    private val fragmentDao = database.fragmentDao()

    override val amountFragments: LiveData<Int> = fragmentDao.getAmount()

    override val actualFragment: LiveData<Int> = fragmentDao.getActualFragment()

    override suspend fun add() {
        fragmentDao.add(Fragments(0))
    }

    override suspend fun remove(model: FragmentModel) {
        fragmentDao.remove(model.currentNumber)
    }

    override suspend fun updateActualFragment(model: FragmentModel) {
        fragmentDao.updateActualFragment(ActualFragment(0, model.currentNumber))
    }

}