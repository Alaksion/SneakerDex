package com.alaksion.sneakerdex.presentation.sneakerdetail

import com.alaksion.sneakerdex.presentation.model.SneakerSizes

interface SneakerSizeListener {
    fun onSizeItemClick(size: SneakerSizes)
}