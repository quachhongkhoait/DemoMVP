package com.example.demomvp.base

import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<VH : RecyclerView.ViewHolder?> constructor(val context: Context) :
    RecyclerView.Adapter<VH>() {

    val resources: Resources
        get() = context.resources

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

}