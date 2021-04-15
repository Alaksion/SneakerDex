package com.alaksion.sneakerdex.presentation.sneakerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.domain.model.Sneaker

class SneakerAdapter : RecyclerView.Adapter<SneakerViewHolder>() {

    private var sneakerList: MutableList<Sneaker> = ArrayList()
    private lateinit var listener: SneakerListClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.sneaker_list_item, parent, false)
        return SneakerViewHolder(
            item,
            listener
        )
    }


    override fun getItemCount(): Int {
        return sneakerList.size
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.bindItemData(sneakerList[position])
    }

    fun addToList(list: List<Sneaker>){
        sneakerList.addAll(list)
        notifyDataSetChanged()
    }

    fun replaceList(list: List<Sneaker>){
        sneakerList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun attachListener(listener: SneakerListClickListener){
        this.listener = listener
    }

}