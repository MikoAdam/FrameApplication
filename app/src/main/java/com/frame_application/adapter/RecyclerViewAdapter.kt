package com.frame_application.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frame_application.R
import com.frame_application.model.Item
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val itemList = mutableListOf<Item>()
    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.item = item
        holder.tvItemName.text = item.itemName
    }

    fun addItem(item: Item) {
        val size = itemList.size
        itemList.add(item)
        notifyItemInserted(size)
    }

    fun addAll(items: List<Item>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteRow(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount() = itemList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName: TextView = itemView.tvItemName
        var item: Item? = null

        init {
            itemView.setOnClickListener {
                item?.let { item -> itemClickListener?.onItemClick(item) }
            }

            itemView.setOnLongClickListener {
                item?.let { itemClickListener?.onItemLongClick(it) }
                true
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(item: Item)
        fun onItemLongClick(item: Item)
    }

}