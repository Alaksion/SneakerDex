package com.alaksion.sneakerdex.presentation.sneakerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.R
import com.alaksion.sneakerdex.databinding.FragmentSneakerListBinding
import com.alaksion.sneakerdex.presentation.sneakerdetail.SneakerDetailActivity
import com.alaksion.sneakerdex.presentation.sneakerlist.adapter.SneakerAdapter
import com.alaksion.sneakerdex.presentation.sneakerlist.listener.SneakerListClickListener

class SneakerListFragment : Fragment() {

    private lateinit var mViewModel: SneakerListViewModel
    private lateinit var viewBinding: FragmentSneakerListBinding

    private val sneakerAdapter = SneakerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(SneakerListViewModel::class.java)
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sneaker_list, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerAdapter()
        setUpRecyclerAutoScrollListener()
        setUpAdapterListener()
        setUpObservers()
        setUpListener()
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
                    mViewModel.handleChangePage()
                }
            }
        })
    }

    private fun setUpAdapterListener() {
        this.sneakerAdapter.attachListener(object : SneakerListClickListener {
            override fun onItemClick(sneakerId: String) {
                SneakerDetailActivity.getInstance(requireActivity(), sneakerId)
            }
        })
    }

    private fun setUpObservers() {
        mViewModel.sneakerList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                if (mViewModel.currentPage.value == 0) {
                    sneakerAdapter.replaceList(it)
                } else {
                    sneakerAdapter.addToList(it)
                }
            }
        })

        mViewModel.validationListener.observe(viewLifecycleOwner, Observer {
            if (!it.getSuccess()) {
                Toast.makeText(requireActivity(), it.getMessage(), Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewBinding.progressbar.visibility = View.VISIBLE
            } else {
                viewBinding.progressbar.visibility = View.GONE
            }
        })

        mViewModel.currentPage.observe(viewLifecycleOwner, Observer {
            mViewModel.getSneakers()
        })

        mViewModel.nameFilter.observe(viewLifecycleOwner, Observer {
            mViewModel.resetPagination()
        })
    }

    private fun setUpListener() {
        viewBinding.ivSearchButton.setOnClickListener() {
            val nameFilter = viewBinding.etSneakerNameFilter.text.toString()
            mViewModel.setNameFilter(nameFilter)
        }

    }

}