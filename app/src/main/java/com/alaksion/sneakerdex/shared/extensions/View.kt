package com.alaksion.sneakerdex.shared.extensions

import android.view.View

object ViewExtension {

    fun View.handleVisibility(isVisibile: Boolean) {
        if (isVisibile) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

