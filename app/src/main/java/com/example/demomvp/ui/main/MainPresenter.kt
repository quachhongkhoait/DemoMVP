package com.example.demomvp.ui.main

import com.example.demomvp.services.ApiService
import com.example.demomvp.services.resposes.FoodResponse
import com.example.demomvp.utils.Constants
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking

class MainPresenter : MainContract.Presenter {
    private var viewMain: MainContract.View? = null
    val gson = GsonBuilder().setPrettyPrinting().create()

    override fun getFoods() {
        viewMain?.onLoading()
        runBlocking {
            val response = gson.fromJson(ApiService().httpGet(Constants.BASE_URL + "food/all"), FoodResponse::class.java)
            if (response.status == 200) {
                viewMain?.onSuccess(response.data)
            } else {
                viewMain?.onError(response.error.toString())
            }
        }
    }

    override fun onStart() {
        getFoods()
    }

    override fun setView(view: MainContract.View?) {
        viewMain = view
    }
}