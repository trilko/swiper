package com.homework.swiper.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.homework.swiper.Interactor
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.presentation.*
import com.homework.swiper.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SwiperViewModel @Inject constructor(
    private val interactor: Interactor
): BaseViewModel() {

    val model = interactor.getModel()

    fun handleEvent(swiperEvents: SwiperEvents) {
        when(swiperEvents) {
            is Plus -> add(swiperEvents.number)
            is Minus -> remove(swiperEvents.number)
            is Change -> change(swiperEvents.number)
            is CreateNotification -> createNotification(swiperEvents.number)
        }
    }

    private fun add(currentNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            model.value?.let {
                interactor.add(FragmentModel(currentNumber, it.amount))
            }
        }
    }

    private fun remove(currentNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            model.value?.let {
                interactor.remove(FragmentModel(currentNumber, it.amount))
            }
        }
    }

    private fun change(currentNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            model.value?.let {
                interactor.change(FragmentModel(currentNumber, it.amount))
            }
        }
    }

    private fun createNotification(number: Int) {

    }
}