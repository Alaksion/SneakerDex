package com.alaksion.sneakerdex.presentation.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.shared.extensions.ImageViewExtensions.setImageFromUrl


class SneakerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val sneakerText: TextView = itemView.findViewById(R.id.tv_name)
    private val sneakerPrice: TextView = itemView.findViewById(R.id.tv_retail_price)
    private val sneakerImage : ImageView = itemView.findViewById(R.id.iv_sneaker_image)
    private val colorwayText: TextView = itemView.findViewById(R.id.tv_colorway)

    fun bindItemData(itemModel: SneakerData){
        val formatSneakerPrice = "$ ${itemModel.retailPrice}"

        sneakerText.text = itemModel.shoe
        sneakerPrice.text = formatSneakerPrice
        colorwayText.text = itemModel.colorway
        sneakerImage.setImageFromUrl(itemModel.media.smallImageUrl)
    }
}