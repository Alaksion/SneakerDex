package br.com.alaksion.alaksion_core.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<B : ViewBinding> : Fragment() {

    private var mBinding: B? = null

    protected val binding
        get() = requireNotNull(mBinding)

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    @CallSuper
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
        setUpListeners()
        postSetup()
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    protected open fun setUpViews() = Unit
    protected open fun setUpObservers() = Unit
    protected open fun setUpListeners() = Unit
    protected open fun postSetup() = Unit


}