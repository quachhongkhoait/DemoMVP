package com.example.demomvp.ui.main

import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvp.R
import com.example.demomvp.base.BaseActivity
import com.example.demomvp.base.BaseAdapter
import com.example.demomvp.extensions.toGone
import com.example.demomvp.extensions.toVisible
import com.example.demomvp.models.Food
import com.example.demomvp.utils.OnLoadMoreListener
import com.example.demomvp.utils.RecyclerViewLoadMoreScroll
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : BaseActivity(), MainContract.View, BaseAdapter.OnItemClickListener<Int> {
    private var dataAll: MutableList<Food> = mutableListOf()
    private var data: MutableList<Food?> = mutableListOf()
    private lateinit var mainAdapter: MainAdapter
    lateinit var mainPresenter: MainPresenter
    lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private var posLoad = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onInit() {
        mainAdapter = MainAdapter(this, data, this)
        val layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        scrollListener = RecyclerViewLoadMoreScroll(layoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
        recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = mainAdapter
            addOnScrollListener(scrollListener)
        }
        initData()
    }

    private fun initData() {
        mainPresenter = MainPresenter().apply {
            setView(this@MainActivity)
            onStart()
        }
    }

    override fun onEvent() {
        swipeRefresh.setOnRefreshListener {
            mainPresenter.getFoods()
            Timer("refresh", false).schedule(2000) {
                swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onSuccess(foods: MutableList<Food>) {
        swipeRefresh.toVisible()
        progressBar.toGone()
        dataAll.addAll(foods)
        data.clear()
        for (it in 0..9) {
            data.add(dataAll[it])
        }
        mainAdapter.notifyDataSetChanged()
    }

    override fun onError(error: String) {
        progressBar.toGone()
        swipeRefresh.toGone()
        textViewError.toVisible()
        textViewError.text = error
    }

    override fun onLoading() {
        progressBar.toVisible()
        swipeRefresh.toGone()
        textViewError.toGone()
    }

    override fun onItemClick(item: Int) {

    }

    private fun loadMoreData() {
        mainAdapter.addLoadingView()
        val start = mainAdapter.itemCount
        var end = start + 10
        posLoad = data.size
        if (end >= dataAll.size) {
            end = dataAll.size
            mainAdapter.setMax()
        }
        Handler().postDelayed({
            for (i in start until end) {
                data.add(dataAll[i])
            }
            mainAdapter.removeLoadingView(posLoad)
            scrollListener.setLoaded()
        }, 2000)
    }
}