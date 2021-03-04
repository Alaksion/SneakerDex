package com.alaksion.sneakerdex.shared.extensions

import android.widget.ImageView
import com.alaksion.sneakerdex.R
import com.squareup.picasso.Picasso

object ImageViewExtensions {

    fun ImageView.setImageFromUrl(url: String?) {
        Picasso.get()
            .load(url)
            .error(R.drawable.sneaker_template)
            .into(this)
    }
}