package com.alaksion.sneakerdex.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.alaksion.sneakerdex.R

class ErrorComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle, defStyleRes) {

    private var inflatedView: View? = null

    var onClick: (() -> Unit)? = null
        set(value) {
            field = value
            inflatedView?.findViewById<AppCompatButton>(R.id.bt_try_again)?.setOnClickListener {
                value?.invoke()
            }
        }

    init {
        inflatedView = LayoutInflater.from(context).inflate(R.layout.component_error, this, true)
    }
}