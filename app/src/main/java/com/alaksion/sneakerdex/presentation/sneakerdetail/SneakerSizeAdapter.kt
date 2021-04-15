package com.alaksion.sneakerdex.presentation.sneakerdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.presentation.model.SneakerSizes


class SneakerSizeAdapter :
    RecyclerView.Adapter<SneakerSizeViewHolder>() {

    private val sizesList: List<SneakerSizes> = SneakerSizes.values().toList()
    private lateinit var listener: SneakerSizeListener
    private var selectedItem: SneakerSizes? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerSizeViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.size_list_item, parent, false)
        return SneakerSizeViewHolder(
            item,
            listener
        )
    }

    override fun getItemCount(): Int {
        return sizesList.size
    }

    override fun onBindViewHolder(holder: SneakerSizeViewHolder, position: Int) {
        holder.bindData(sizesList[position], selectedItem)
    }

    fun attachListener(listener: SneakerSizeListener) {
        this.listener = listener
    }

    fun handleItemChange(newSelectedItem: SneakerSizes) {
        if(newSelectedItem != this.selectedItem){
            this.selectedItem = newSelectedItem
            notifyDataSetChanged()
        }
    }
}