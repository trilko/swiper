package com.homework.swiper.presentation

sealed class SwiperEvents

class Plus(val number: Int): SwiperEvents()
class Minus(val number: Int): SwiperEvents()
class Change(val number: Int): SwiperEvents()
class CreateNotification(val number: Int): SwiperEvents()
