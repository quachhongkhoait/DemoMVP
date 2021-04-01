package com.example.demomvp.ui.main

import android.os.Handler
import android.os.Looper
import com.example.demomvp.models.Food
import com.example.demomvp.services.ApiService
import com.example.demomvp.utils.Constants
import org.json.JSONObject


class MainPresenter : MainContract.Presenter {
    private var viewMain: MainContract.View? = null
    private var data: MutableList<Food> = mutableListOf()

    override fun getFoods() {
        val thread = Thread {
            with(ApiService().httpGet(Constants.BASE_URL + "food/all")) {
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    val jsonObject = JSONObject(this)
                    if (jsonObject.getInt(Constants.GET_STATUS) == 200) {
                        val list = jsonObject.getJSONArray(Constants.GET_DATA)
                        for (it in 0 until list.length()) {
                            list.getJSONObject(it).run {
                                data.add(
                                    Food(
                                        getInt(Constants.GET_ID),
                                        getInt(Constants.GET_CATEGORYID),
                                        getInt(Constants.GET_SPECIALTIESID),
                                        getString(Constants.GET_TITLE),
                                        getString(Constants.GET_THUMBNAIL),
                                        getString(Constants.GET_DESCRIPTION)
                                    )
                                )
                            }
                        }
                        viewMain?.onSuccess(data)
                    } else {
                        viewMain?.onError(jsonObject.getString(Constants.GET_MESSAGE))
                    }
                }
            }
        }
        thread.start()
    }

    override fun onStart() = getFoods()

    override fun setView(view: MainContract.View?) {
        viewMain = view
    }
}
