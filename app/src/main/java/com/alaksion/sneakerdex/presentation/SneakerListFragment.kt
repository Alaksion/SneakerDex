package com.alaksion.sneakerdex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alaksion.sneakerdex.R

class SneakerListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_sneaker_list, container, false)
    }

    companion object {
        fun getInstance() : SneakerListFragment{
            return SneakerListFragment()
        }
    }
}