package com.alaksion.sneakerdex.shared.extensions

object BooleanExtensions {

    fun Boolean?.handleOptional(): Boolean {
        if (this == null) {
            return false
        }
        return this
    }
}