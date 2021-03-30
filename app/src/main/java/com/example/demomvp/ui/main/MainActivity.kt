package com.example.demomvp.ui.main

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvp.R
import com.example.demomvp.base.BaseActivity
import com.example.demomvp.base.BaseAdapter
import com.example.demomvp.models.Food
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View, BaseAdapter.OnItemClickListener<Int> {
    private var data: MutableList<Food> = mutableListOf()
    private lateinit var mainAdapter: MainAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        mainAdapter = MainAdapter(this, data, this)
        recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
        }
        initData()
    }

    private fun initData() {
        MainPresenter().apply {
            setView(this@MainActivity)
            onStart()
        }
    }

    override fun onEvent() {

    }

    override fun onSuccess(foods: MutableList<Food>) {
        data.addAll(foods)
        mainAdapter.notifyDataSetChanged()
    }

    override fun onError(error: String) {
        Log.d(TAG, "onError: $error")
    }

    override fun onLoading() {
        Log.d(TAG, "onLoading")
    }

    override fun onItemClick(item: Int) {

    }
}