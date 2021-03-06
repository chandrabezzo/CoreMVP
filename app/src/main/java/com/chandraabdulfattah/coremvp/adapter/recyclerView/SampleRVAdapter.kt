package com.chandraabdulfattah.coremvp.adapter.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.listener.OnItemClickListener
import com.chandraabdulfattah.coremvp.ui.base.BaseHolder

/**
 * Created by bezzo on 11/01/18.
 * Change String to model you need convert to recycler view
 */
class SampleRVAdapter(var list : ArrayList<String>,
                      var context : Context)
    : RecyclerView.Adapter<SampleRVAdapter.Item>() {

    // uncomment if you use click listener
//    lateinit var listener : OnItemClickListener
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_rv_sample, parent, false))
    }


    inner class Item(itemView : View) : BaseHolder<String>(itemView){

        init {
            // add listener if you need
//            itemView.setOnClickListener {
//                listener.onItemClick(it, layoutPosition)
//            }
//
//            itemView.setOnLongClickListener {
//                listener.onItemLongClick(it, layoutPosition)
//            }
        }

        override fun setContent(model: String) {
            // set content item
        }
    }
}