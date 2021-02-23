package com.alaksion.sneakerdex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.alaksion.sneakerdex.R

class SneakerListFragment : Fragment() {

    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        return inflater.inflate(R.layout.fragment_sneaker_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getSneakers()
    }
}