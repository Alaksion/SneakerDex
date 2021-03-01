package com.alaksion.sneakerdex.presentation.sneakerdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.domain.usecase.GetSneakerUseCase
import com.alaksion.sneakerdex.presentation.model.SneakerSizes
import com.alaksion.sneakerdex.shared.listeners.ApiListener
import com.alaksion.sneakerdex.shared.listeners.ValidationListener

class SneakerDetailViewModel(application: Application): AndroidViewModel(application) {

    private val getSneakerUseCase = GetSneakerUseCase()

    private val mValidationListener = MutableLiveData<ValidationListener>()
    var validationListener : LiveData<ValidationListener> = mValidationListener

    private val mSneakerInfo = MutableLiveData<SneakerData>()
    var sneakerInfo : LiveData<SneakerData> = mSneakerInfo

    private val mIsLoading = MutableLiveData<Boolean>()
    var isLoading : LiveData<Boolean> = mIsLoading

    private val mSelectedSize = MutableLiveData<SneakerSizes>()
    var selectedSize : LiveData<SneakerSizes> = mSelectedSize


    fun getSneaker(sneakerId: String){
        mIsLoading.value = true

        getSneakerUseCase.execute(object : ApiListener<SneakerResponseData>{
            override fun onSuccess(response: SneakerResponseData) {
                mSneakerInfo.value = response.results[0]
                mIsLoading.value = false
            }

            override fun onError(errorMessage: String) {
                mValidationListener.value = ValidationListener(errorMessage)
                mIsLoading.value = false
            }
        }, sneakerId)
    }

    fun selectSneakerSize(sneakerSizes: SneakerSizes){
        mSelectedSize.value = sneakerSizes
    }
}