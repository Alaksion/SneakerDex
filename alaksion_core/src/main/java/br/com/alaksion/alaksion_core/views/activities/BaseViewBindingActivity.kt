package br.com.alaksion.alaksion_core.views.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingActivity<B : ViewBinding> : AppCompatActivity() {

    private var mBinding: B? = null

    protected val binding
        get() = requireNotNull(mBinding)

    abstract val bindingInflater: (LayoutInflater) -> B

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        mBinding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setUpViews()
        setUpObservers()
        setUpListeners()
        postSetup()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


    protected open fun setUpViews() = Unit
    protected open fun setUpObservers() = Unit
    protected open fun setUpListeners() = Unit
    protected open fun postSetup() = Unit

}