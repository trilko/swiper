package com.homework.swiper.dependencies.modules

import com.homework.swiper.FragmentRepository
import com.homework.swiper.Interactor
import com.homework.swiper.domain.HandlerEvents
import com.homework.swiper.domain.ValidatorModel
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
object DomainModule {

    @Provides
    fun getInteractor(
        repository: FragmentRepository,
        validator: ValidatorModel
    ): Interactor = HandlerEvents(repository, validator)
}