package com.homework.swiper.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.swiper.Interactor
import com.homework.swiper.data.models.FragmentModel
import com.homework.swiper.presentation.*
import kotlinx.coroutines.*
import javax.inject.Inject

class ViewModelSwiper @Inject constructor(
    private val interactor: Interactor
): ViewModel() {

    val model = interactor.getModel()

    fun handleEvent(event: Event) {
        when(event) {
            is Plus -> add(event.number)
            is Minus -> remove(event.number)
            is Change -> change(event.number)
            is CreateNotification -> createNotification(event.number)
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