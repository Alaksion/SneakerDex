package com.alaksion.sneakerdex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alaksion.sneakerdex.R

class FavoriteSneakersListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_sneakers_list, container, false)
    }

    companion object {
        fun getInstance() : FavoriteSneakersListFragment{
            return FavoriteSneakersListFragment()
        }
    }

}