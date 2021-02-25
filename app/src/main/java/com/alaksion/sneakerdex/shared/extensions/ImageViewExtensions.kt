package com.alaksion.sneakerdex.shared.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageViewExtensions {

    fun ImageView.setImageFromUrl(url: String) {
        Picasso.get()
            .load(url)
            .into(this)

    }
}