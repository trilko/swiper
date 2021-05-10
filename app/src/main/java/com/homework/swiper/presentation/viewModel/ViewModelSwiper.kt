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

    val amountFragments = interactor.getAmountFragments()
    val currentFragment = interactor.getActualFragment()

    fun handleEvent(event: Event) {
        when(event) {
            is Plus -> add(event.number)
            is Minus -> remove(event.number)
            is Change -> change(event.number)
            is CreateNotification -> createNotification(event.number)
        }
    }

    private fun add(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.add()
        }
    }

    private fun remove(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.remove(FragmentModel(number))
        }
    }

    private fun change(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.change(FragmentModel(number))
        }
    }

    private fun createNotification(number: Int) {

    }
}