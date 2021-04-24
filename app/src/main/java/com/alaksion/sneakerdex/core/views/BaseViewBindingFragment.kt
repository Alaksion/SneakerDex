package com.alaksion.sneakerdex.core.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<B : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) -> B

    @Suppress("UNCHECKED_CAST")
    protected val binding: B
        get() = requireNotNull(_binding) as B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
        setUpListener()
    }

    protected open fun setUpViews() {}
    protected open fun setUpObservers() {}
    protected open fun setUpListener() {}
}