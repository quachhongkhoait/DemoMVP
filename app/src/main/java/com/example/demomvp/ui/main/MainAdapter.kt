package com.example.demomvp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvp.R
import com.example.demomvp.base.BaseAdapter
import com.example.demomvp.base.BaseHolder
import com.example.demomvp.models.Food
import kotlinx.android.synthetic.main.viewholder_item_food.view.*

class MainAdapter(
    context: Context,
    private val data: MutableList<Food>,
    val listener: OnItemClickListener<Int>
) :
    BaseAdapter<MainAdapter.ItemViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_item_food, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(context, data, position)
    }

    override fun getItemCount(): Int = data.size

    inner class ItemViewHolder(itemView: View) : BaseHolder<Food>(itemView) {
        override fun bindData(context: Context, listData: List<Food>, position: Int) {
            itemView.textViewName.text = listData[position].title
            itemView.relativeOnClickListener.setOnClickListener { listener.onItemClick(position) }
        }
    }
}