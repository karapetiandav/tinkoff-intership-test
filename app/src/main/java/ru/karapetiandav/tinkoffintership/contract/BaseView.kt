package ru.karapetiandav.tinkoffintership.contract

interface BaseView<T> {
    fun setPresenter(presenter: T)
}