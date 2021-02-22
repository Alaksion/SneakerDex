package com.alaksion.sneakerdex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
        setUpBottomNavListener()
        setUpNavigationDefaultPage()
    }

    private fun setUpNavigationDefaultPage(){
        viewBinding.bnMenu.selectedItemId = R.id.mi_sneaker_list
    }

    private fun setUpBottomNavListener() {
        viewBinding.bnMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mi_favorites_list -> {
                    handleFragmentChange(FavoriteSneakersListFragment.getInstance())
                    true
                }
                R.id.mi_sneaker_list -> {
                    handleFragmentChange(SneakerListFragment.getInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun handleFragmentChange(frag: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(viewBinding.flFragmentHolder.id, frag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}