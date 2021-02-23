package com.alaksion.sneakerdex.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.presentation.viewholder.SneakerViewHolder

class SneakerAdapter : RecyclerView.Adapter<SneakerViewHolder>() {

    private var sneakerList: List<SneakerData> = arrayListOf<SneakerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.sneaker_list_item, parent, false)
        return SneakerViewHolder(item)
    }


    override fun getItemCount(): Int {
        return sneakerList.size
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.bindItemData(sneakerList[position])
    }

    fun updateList(list: List<SneakerData>){
        sneakerList = list
        notifyDataSetChanged()
    }


}