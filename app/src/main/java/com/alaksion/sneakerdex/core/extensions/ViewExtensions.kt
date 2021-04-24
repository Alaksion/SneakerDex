package com.alaksion.sneakerdex.core.extensions

import android.view.View

object ViewExtensions {

    fun View.handleVisibility(isVisible: Boolean) {
        if (isVisible) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }
}

