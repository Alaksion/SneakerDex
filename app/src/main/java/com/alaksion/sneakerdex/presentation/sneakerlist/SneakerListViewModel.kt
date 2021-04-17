package com.alaksion.sneakerdex.presentation.sneakerlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alaksion.sneakerdex.domain.model.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.model.SneakerListResponse
import com.alaksion.sneakerdex.domain.usecase.GetSneakersListUseCase
import com.alaksion.sneakerdex.shared.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SneakerListViewModel(
    application: Application,
    private val useCase: GetSneakersListUseCase
) : AndroidViewModel(application) {

    companion object {
        const val RESULTS_PER_PAGE = "10"
    }

    private val mSneakersList = MutableLiveData<Resource<SneakerListResponse>>()
    var sneakerList: LiveData<Resource<SneakerListResponse>> = mSneakersList

    private val mIsLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = mIsLoading

    private val mCurrentPage = MutableLiveData(0)
    var currentPage: LiveData<Int> = mCurrentPage

    private val mNameFilter = MutableLiveData<String>()
    var nameFilter: LiveData<String> = mNameFilter

    fun getSneakers() {
        mIsLoading.value = true

        val requestParams = GetSneakersRequestParams(
            RESULTS_PER_PAGE,
            mCurrentPage.value.toString(),
            null,
            mNameFilter.value,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        viewModelScope.launch {
            val result = useCase.invoke(requestParams)

            withContext(Dispatchers.Main) {
                mSneakersList.value = result
                mIsLoading.value = false
            }
        }

    }

    fun handleMoveToNextPage() {
        mCurrentPage.value = mCurrentPage.value?.plus(1)
    }

    fun resetPagination() {
        mCurrentPage.value = 0
    }

    fun setNameFilter(nameFilter: String) {
        mNameFilter.value = if (nameFilter.isEmpty()) null else nameFilter
    }
}