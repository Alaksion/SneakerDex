package com.alaksion.sneakerdex.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.databinding.FragmentSneakerListBinding
import com.alaksion.sneakerdex.presentation.adapter.SneakerAdapter

class SneakerListFragment : Fragment() {

    private lateinit var mViewModel: SneakerViewModel
    private lateinit var viewBinding: FragmentSneakerListBinding

    private val sneakerAdapter = SneakerAdapter()

    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(SneakerViewModel::class.java)
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sneaker_list, container, false)


        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerAdapter()
        setUpRecyclerAutoScrollListener()
        setUpObservers()
    }

    override fun onResume() {
        super.onResume()
        loadSneakersList()
    }

    private fun setupRecyclerAdapter() {
        val recyclerView = viewBinding.rvSneakers
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = sneakerAdapter
    }

    private fun setUpRecyclerAutoScrollListener() {

        viewBinding.rvSneakers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    currentPage++
                    loadSneakersList()
                }
            }
        })
    }

    private fun loadSneakersList() {
        mViewModel.getSneakers(currentPage.toString())
    }

    private fun setUpObservers() {
        mViewModel.sneakerList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                sneakerAdapter.updateList(it)
            }
        })

        mViewModel.validationListener.observe(viewLifecycleOwner, Observer {
            if (!it.getSuccess()) {
                Toast.makeText(requireActivity(), it.getMessage(), Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it) {
                viewBinding.progressbar.visibility = View.VISIBLE
            } else {
                viewBinding.progressbar.visibility = View.GONE
            }
        })

    }

}