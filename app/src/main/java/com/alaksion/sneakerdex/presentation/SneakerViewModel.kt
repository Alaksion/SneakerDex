package com.alaksion.sneakerdex.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.repository.request.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.usecase.GetSneakersListUseCase
import com.alaksion.sneakerdex.shared.listeners.ApiListener
import com.alaksion.sneakerdex.shared.listeners.ValidationListener

class SneakerViewModel(application: Application) : AndroidViewModel(application) {

    private val getSneakersUseCase = GetSneakersListUseCase()

    private val mSneakersList = MutableLiveData<List<SneakerData>>()
    var sneakerList : LiveData<List<SneakerData>> = mSneakersList

    private val mValidationListener = MutableLiveData<ValidationListener>()
    var validationListener : LiveData<ValidationListener> = mValidationListener

    private val mIsLoading = MutableLiveData<Boolean>()
    var isLoading : LiveData<Boolean> = mIsLoading

    fun getSneakers(currentPage: String){
        mIsLoading.value = true

        val requestParams = GetSneakersRequestParams(
            "10",
            currentPage,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        getSneakersUseCase.execute(object : ApiListener<SneakerResponseData> {
            override fun onSuccess(response: SneakerResponseData) {
                mSneakersList.value = response.results
                mIsLoading.value = false
            }

            override fun onError(errorMessage: String) {
                mValidationListener.value = ValidationListener(errorMessage)
            }

        }, requestParams)
    }
}