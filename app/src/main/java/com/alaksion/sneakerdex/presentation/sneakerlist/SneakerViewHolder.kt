package com.alaksion.sneakerdex.presentation.sneakerlist

import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.databinding.SneakerListItemBinding
import com.alaksion.sneakerdex.domain.model.Sneaker
import com.alaksion.sneakerdex.core.extensions.ImageViewExtensions.setImageFromUrl

class SneakerViewHolder(
    private val binding: SneakerListItemBinding,
    private val clickListener: SneakerListClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItemData(itemModel: Sneaker) = with(binding) {
        val formatSneakerPrice = "$ ${itemModel.retailPrice}"

        tvSneakerName.text = itemModel.shoe
        tvRetailPrice.text = formatSneakerPrice
        ivSneakerImage.setImageFromUrl(itemModel.media.smallImageUrl)
        tvBrandName.text = itemModel.brand

        clItemContainer.setOnClickListener {
            clickListener.onItemClick(itemModel.id)
        }
    }
}