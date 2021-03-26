package com.alaksion.sneakerdex.presentation.sneakerlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alaksion.sneakerdex.databinding.FragmentSneakerListBinding
import com.alaksion.sneakerdex.domain.model.Sneaker
import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.alaksion.sneakerdex.presentation.sneakerdetail.SneakerDetailActivity
import com.alaksion.sneakerdex.presentation.sneakerlist.adapter.SneakerAdapter
import com.alaksion.sneakerdex.presentation.sneakerlist.listener.SneakerListClickListener
import com.alaksion.sneakerdex.shared.extensions.BooleanExtensions.handleOptional
import com.alaksion.sneakerdex.shared.network.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class SneakerListFragment : Fragment() {

    private val mViewModel by viewModel<SneakerListViewModel>()
    private lateinit var viewBinding: FragmentSneakerListBinding

    private val sneakerAdapter = SneakerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentSneakerListBinding.inflate(layoutInflater)
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
        mViewModel.sneakerList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> updateSneakerList(response.data)
                is Resource.Error -> Toast.makeText(context, response.errorMsg, Toast.LENGTH_SHORT)
                    .show()
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
        viewBinding.ivSearchButton.setOnClickListener {
            val nameFilter = viewBinding.etSneakerNameFilter.text.toString()
            mViewModel.setNameFilter(nameFilter)
        }
    }

    private fun updateSneakerList(apiResponse: SneakerListResponse?) {
        apiResponse.let { response ->
            val list = response?.results

            if (list?.isNotEmpty().handleOptional()) {
                incrementOrReplaceList(list)
            }
        }
    }

    private fun incrementOrReplaceList(list: List<Sneaker>?) {
        if (mViewModel.currentPage.value == 0) {
            sneakerAdapter.replaceList(list!!)
        } else {
            sneakerAdapter.addToList(list!!)
        }

    }
}



