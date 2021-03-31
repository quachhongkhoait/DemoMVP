package com.example.demomvp.ui.main

import com.example.demomvp.base.BasePresenter
import com.example.demomvp.models.Food

interface MainContract {
    interface View {
        fun onSuccess(foods: MutableList<Food>)
        fun onError(error: String)
        fun onLoading()
    }

    interface Presenter : BasePresenter<View> {
        fun getFoods()
    }
}