package com.example.demomvp.ui.main

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demomvp.R
import com.example.demomvp.base.BaseAdapter
import com.example.demomvp.base.BaseHolder
import com.example.demomvp.extensions.loadFromUrl
import com.example.demomvp.extensions.toGone
import com.example.demomvp.extensions.toVisible
import com.example.demomvp.models.Food
import com.example.demomvp.utils.Constants
import kotlinx.android.synthetic.main.progress_loading.view.*
import kotlinx.android.synthetic.main.viewholder_item_food.view.*


class MainAdapter(
    context: Context,
    private var data: MutableList<Food?>,
    val listener: OnItemClickListener<Int>
) :
    BaseAdapter<BaseHolder<Food?>>(context) {
    var positionMax = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Food?> {
        return if (viewType == Constants.VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_item_food, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<Food?>, position: Int) {
        if (holder.itemViewType == Constants.VIEW_TYPE_ITEM) {
            holder.bindData(context, data, position)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ItemViewHolder(itemView: View) : BaseHolder<Food?>(itemView) {
        override fun bindData(context: Context, listData: List<Food?>, position: Int) {
            itemView.textViewName.text = listData[position]?.title
            itemView.relativeOnClickListener.setOnClickListener { listener.onItemClick(position) }
            itemView.imageView.loadFromUrl(Constants.BASE_URL_IMAGE + listData[position]?.thumbnail)
        }
    }

    inner class LoadingViewHolder(itemView: View) : BaseHolder<Food?>(itemView) {
        override fun bindData(context: Context, listData: List<Food?>, position: Int) {
            if (positionMax) {
                itemView.textViewMax.toVisible()
                itemView.progressBar.toGone()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] == null) {
            Constants.VIEW_TYPE_LOADING
        } else Constants.VIEW_TYPE_ITEM
    }

    fun setMax() {
        positionMax = true
    }

    fun getItemAtPosition(position: Int): Food? {
        return data[position]
    }

    fun addLoadingView() {
        Handler().post {
            data.add(null)
            notifyItemInserted(data.size - 1)
        }
    }

    fun removeLoadingView(pos: Int) {
        if (data.size != 0) {
            data.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }
}
