package com.example.demomvp.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        protected val TAG = "BaseHolder"
    }
    abstract fun bindData(context: Context, listData: List<T>, position: Int)
}