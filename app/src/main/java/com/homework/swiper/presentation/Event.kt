package com.homework.swiper.presentation

sealed class Event

class Plus(val number: Int): Event()
class Minus(val number: Int): Event()
class Change(val number: Int): Event()
class CreateNotification(val number: Int): Event()
