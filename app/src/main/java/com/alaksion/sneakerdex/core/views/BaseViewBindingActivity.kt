package com.alaksion.sneakerdex.core.views

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> B

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = _binding as B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)
        setUpViews()
        setUpObservers()
        setUpListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun setUpViews() {}
    protected open fun setUpObservers() {}
    protected open fun setUpListeners() {}
}