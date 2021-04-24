package com.alaksion.sneakerdex.presentation.sneakerlist

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
import com.alaksion.sneakerdex.core.extensions.BooleanExtensions.handleOptional
import com.alaksion.sneakerdex.core.network.Resource
import com.alaksion.sneakerdex.core.views.BaseViewBindingFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SneakerListFragment : BaseViewBindingFragment<FragmentSneakerListBinding>() {

    private val mViewModel by viewModel<SneakerListViewModel>()
    override val bindingInflater: (inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) -> FragmentSneakerListBinding =
        FragmentSneakerListBinding::inflate

    private val sneakerAdapter = SneakerAdapter()

    private fun setupRecyclerAdapter() {
        val recyclerView = binding.rvSneakers
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = sneakerAdapter
    }

    private fun setUpRecyclerAutoScrollListener() {

        binding.rvSneakers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    mViewModel.handleMoveToNextPage()
                }
            }
        })
    }

    private fun setUpAdapterListener() {
        this.sneakerAdapter.attachListener(object :
            SneakerListClickListener {
            override fun onItemClick(sneakerId: String) {
                SneakerDetailActivity.getInstance(requireActivity(), sneakerId)
            }
        })
    }

    private fun updateSneakerList(apiResponse: SneakerListResponse?) {
        binding.ecSneakerList.visibility = View.GONE
        apiResponse.let { response ->
            val list = response?.results

            if (list?.isNotEmpty().handleOptional()) {
                incrementOrReplaceList(list)
            }
        }
    }

    private fun incrementOrReplaceList(list: List<Sneaker>?) {
        list?.run {
            if (mViewModel.currentPage.value == 0) {
                sneakerAdapter.replaceList(this)
            } else {
                sneakerAdapter.addToList(this)
            }
        }
    }

    private fun hideLoaderIfPageIsLoaded(isLoading: Boolean) {
        if (isLoading) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    private fun handleAPiError(error: String) {
        if (sneakerAdapter.itemCount > 0) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        } else {
            binding.ecSneakerList.visibility = View.VISIBLE
        }
    }

    override fun setUpObservers() {
        mViewModel.sneakerList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> updateSneakerList(response.data)
                is Resource.Error -> handleAPiError(response.errorMsg)
            }
        })

        mViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            hideLoaderIfPageIsLoaded(it)
        })

        mViewModel.currentPage.observe(viewLifecycleOwner, Observer {
            mViewModel.getSneakers()
        })

        mViewModel.nameFilter.observe(viewLifecycleOwner, Observer {
            mViewModel.resetPagination()
        })
    }

    override fun setUpListener() {
        binding.ivSearchButton.setOnClickListener {
            val nameFilter = binding.etSneakerNameFilter.text.toString()
            mViewModel.setNameFilter(nameFilter)
        }

        binding.ecSneakerList.onClick = { mViewModel.getSneakers() }
    }

    override fun setUpViews() {
        setupRecyclerAdapter()
        setUpRecyclerAutoScrollListener()
        setUpAdapterListener()
    }
}



