package com.alaksion.sneakerdex.presentation.sneakerlist.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.presentation.sneakerlist.listener.SneakerListClickListener
import com.alaksion.sneakerdex.shared.extensions.ImageViewExtensions.setImageFromUrl
import org.w3c.dom.Text


class SneakerViewHolder(itemView: View, private val clickListener: SneakerListClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val sneakerText: TextView = itemView.findViewById(R.id.tv_sneaker_name)
    private val sneakerPrice: TextView = itemView.findViewById(R.id.tv_retail_price)
    private val sneakerImage: ImageView = itemView.findViewById(R.id.iv_sneaker_image)
    private val sneakerBrand: TextView = itemView.findViewById(R.id.tv_brand_name)
    private val itemContainer: ConstraintLayout = itemView.findViewById(R.id.cl_item_container)

    fun bindItemData(itemModel: SneakerData) {
        val formatSneakerPrice = "$ ${itemModel.retailPrice}"

        sneakerText.text = itemModel.shoe
        sneakerPrice.text = formatSneakerPrice
        sneakerImage.setImageFromUrl(itemModel.media.smallImageUrl)
        sneakerBrand.text = itemModel.brand


        itemContainer.setOnClickListener {
            clickListener.onItemClick(itemModel.id)

        }
    }
}