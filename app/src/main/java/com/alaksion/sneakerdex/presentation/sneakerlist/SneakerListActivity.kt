package com.alaksion.sneakerdex.presentation.sneakerlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alaksion.sneakerdex.databinding.ActivityMainBinding

class SneakerListActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}