package br.com.alaksion.alaksion_core.views.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseBindingViewHolder<B : ViewBinding, in T>(
    protected val binding: B
) : RecyclerView.ViewHolder(binding.root) {

    protected val context: Context by lazy { binding.root.context }

    protected open fun bind(item: T){}

}