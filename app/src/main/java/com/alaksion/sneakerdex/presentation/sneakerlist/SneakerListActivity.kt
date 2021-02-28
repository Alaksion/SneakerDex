package com.alaksion.sneakerdex.presentation.sneakerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.databinding.ActivityMainBinding


class SneakerListActivity : AppCompatActivity() {

    private val layoutId = R.layout.activity_main
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(viewBinding.root)
    }
}