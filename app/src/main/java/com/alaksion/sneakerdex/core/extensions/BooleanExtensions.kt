package com.alaksion.sneakerdex.core.extensions

object BooleanExtensions {

    fun Boolean?.handleOptional(): Boolean {
        if (this == null) {
            return false
        }
        return this
    }
}