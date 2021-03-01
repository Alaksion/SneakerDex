package com.alaksion.sneakerdex.presentation.sneakerdetail.viewholder

import android.graphics.Color
import android.provider.CalendarContract
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.presentation.model.SneakerSizes
import com.alaksion.sneakerdex.presentation.sneakerdetail.listener.SneakerSizeListener

class SneakerSizeViewHolder(itemView: View, val listener: SneakerSizeListener) :
    RecyclerView.ViewHolder(itemView) {

    private val sizeText: TextView = itemView.findViewById(R.id.tv_size_description)

    fun bindData(
        item: SneakerSizes,
        selectedItem: SneakerSizes?
    ) {
        val currentItemIsSelected = item == selectedItem
        sizeText.text = item.description

        sizeText.setTextColor(
            if (currentItemIsSelected) {
                Color.WHITE
            } else {
                Color.BLACK
            }
        )

        sizeText.isSelected = currentItemIsSelected

        sizeText.setOnClickListener() {
            listener.onSizeItemClick(item)
        }
    }
}