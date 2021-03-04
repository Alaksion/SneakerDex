package com.alaksion.sneakerdex.presentation.sneakerlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerListResponseData
import com.alaksion.sneakerdex.data.repository.request.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.usecase.GetSneakersListUseCase
import com.alaksion.sneakerdex.shared.listeners.ApiListener
import com.alaksion.sneakerdex.shared.listeners.ValidationListener

class SneakerListViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val RESULTS_PER_PAGE = "10"
    }

    private val getSneakersUseCase = GetSneakersListUseCase()

    private val mSneakersList = MutableLiveData<List<SneakerData>>()
    var sneakerList : LiveData<List<SneakerData>> = mSneakersList

    private val mValidationListener = MutableLiveData<ValidationListener>()
    var validationListener : LiveData<ValidationListener> = mValidationListener

    private val mIsLoading = MutableLiveData<Boolean>()
    var isLoading : LiveData<Boolean> = mIsLoading

    private val mCurrentPage = MutableLiveData(0)
    var currentPage : LiveData<Int> = mCurrentPage

    private val mNameFilter = MutableLiveData<String>()
    var nameFilter: LiveData<String> = mNameFilter

    fun getSneakers(){
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

        getSneakersUseCase.execute(object : ApiListener<SneakerListResponseData> {
            override fun onSuccess(response: SneakerListResponseData) {
                mSneakersList.value = response.results
                mIsLoading.value = false
            }

            override fun onError(errorMessage: String) {
                mValidationListener.value = ValidationListener(errorMessage)
                mIsLoading.value = false
            }

        }, requestParams)
    }

    fun handleChangePage() {
        mCurrentPage.value = mCurrentPage.value?.plus(1)
    }

    fun resetPagination() {
        mCurrentPage.value = 0
    }

    fun setNameFilter(nameFilter: String){
        mNameFilter.value = if(nameFilter.isEmpty()) null else nameFilter
    }
}