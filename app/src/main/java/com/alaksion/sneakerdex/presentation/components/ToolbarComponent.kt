package com.alaksion.sneakerdex.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.alaksion.sneakerdex.R

class ToolbarComponent @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.component_toolbar, this, true)
    }
}