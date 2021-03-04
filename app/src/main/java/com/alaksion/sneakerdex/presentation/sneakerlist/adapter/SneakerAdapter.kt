package com.alaksion.sneakerdex.presentation.sneakerlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.presentation.sneakerlist.listener.SneakerListClickListener
import com.alaksion.sneakerdex.presentation.sneakerlist.viewholder.SneakerViewHolder

class SneakerAdapter : RecyclerView.Adapter<SneakerViewHolder>() {

    private var sneakerList: MutableList<SneakerData> = ArrayList()
    private lateinit var listener: SneakerListClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.sneaker_list_item, parent, false)
        return SneakerViewHolder(item, listener)
    }


    override fun getItemCount(): Int {
        return sneakerList.size
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.bindItemData(sneakerList[position])
    }

    fun addToList(list: List<SneakerData>){
        sneakerList.addAll(list)
        notifyDataSetChanged()
    }

    fun replaceList(list: List<SneakerData>){
        sneakerList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun attachListener(listener: SneakerListClickListener){
        this.listener = listener
    }

}