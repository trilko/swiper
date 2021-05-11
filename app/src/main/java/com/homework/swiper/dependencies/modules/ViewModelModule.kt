package com.homework.swiper.dependencies.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.homework.swiper.presentation.viewModel.SwiperViewModel
import com.homework.swiper.utils.ViewModelFactory
import com.homework.swiper.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SwiperViewModel::class)
    internal abstract fun bindNoteViewModel(viewModel: SwiperViewModel): ViewModel
}