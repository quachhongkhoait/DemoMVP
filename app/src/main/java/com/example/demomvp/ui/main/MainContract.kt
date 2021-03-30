package com.example.demomvp.ui.main

import com.example.demomvp.base.BasePresenter
import com.example.demomvp.models.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

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