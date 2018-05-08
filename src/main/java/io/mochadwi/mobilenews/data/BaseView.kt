package io.mochadwi.mobilenews.data

interface BaseView<T> {

    fun setPresenter(presenter: T)

    fun showToast(message: String)

    fun showProgress()

    fun hideProgress()
}