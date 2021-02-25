package com.alaksion.sneakerdex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val layoutId = R.layout.activity_main
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        setContentView(viewBinding.root)
        setUpBottomNavigation()
    }


    private fun setUpBottomNavigation() {
        val navController = findNavController(R.id.f_nav_host)
        viewBinding.bnMenu.setupWithNavController(navController)
    }
    
}