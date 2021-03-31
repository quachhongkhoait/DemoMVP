package com.example.demomvp.ui.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.demomvp.models.Food
import com.example.demomvp.services.ApiService
import com.example.demomvp.utils.Constants
import org.json.JSONObject
import kotlin.concurrent.thread


class MainPresenter : MainContract.Presenter {
    private var viewMain: MainContract.View? = null
    private var data: MutableList<Food> = mutableListOf()

    override fun getFoods() {
        val thread = Thread {
            with(ApiService().httpGet(Constants.BASE_URL + "food/all")) {
                val jsonObject = JSONObject(this)
                Log.d("nnn", "getFoods: " + jsonObject.getInt("status"))
                if (jsonObject.getInt("status") == 200) {
                    val list = jsonObject.getJSONArray("data")
                    for (it in 0 until list.length()) {
                        data.add(
                            Food(
                                list.getJSONObject(it).getInt("id"),
                                list.getJSONObject(it).getInt("categoryId"),
                                list.getJSONObject(it).getInt("specialtiesId"),
                                list.getJSONObject(it).getString("title"),
                                list.getJSONObject(it).getString("thumbnail"),
                                list.getJSONObject(it).getString("description"),
                            )
                        )
                    }
                    viewMain?.onSuccess(data)
                } else {
                    viewMain?.onError(jsonObject.getString("message"))
                }
            }
        }
        thread.start()


        val handler = Handler(Looper.getMainLooper())
        handler.post {
            thread {

            }
        }
    }

    override fun onStart() = getFoods()

    override fun setView(view: MainContract.View?) {
        viewMain = view
    }
}
