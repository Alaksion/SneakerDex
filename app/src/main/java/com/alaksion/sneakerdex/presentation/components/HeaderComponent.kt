package com.alaksion.sneakerdex.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.core.extensions.ViewExtensions.handleVisibility
import com.alaksion.sneakerdex.core.extensions.BooleanExtensions.handleOptional

class HeaderComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle, defStyleRes) {

    var onBackClick: (() -> Unit)? = null
        set(value) {
            field = value
            inflatedView?.findViewById<ImageView>(R.id.iv_header_back_button)?.setOnClickListener {
                onBackClick?.invoke()
            }
        }

    var showBackButton: Boolean? = null
        set(value) {
            field = value
            inflatedView?.findViewById<ImageView>(R.id.iv_header_back_button)
                ?.handleVisibility(field.handleOptional())
        }

    private var inflatedView: View? = null

    init {
        var showBackButton: Boolean? = null
        inflatedView = LayoutInflater.from(context).inflate(R.layout.component_header, this, true)
        attributeSet.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.HeaderComponent,
                defStyle,
                defStyleRes
            )
            showBackButton = typedArray.getBoolean(R.styleable.HeaderComponent_showBackButton, true)

            this@HeaderComponent.showBackButton = showBackButton
        }
    }
}