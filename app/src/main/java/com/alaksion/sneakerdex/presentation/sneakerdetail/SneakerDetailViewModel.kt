package com.alaksion.sneakerdex.presentation.sneakerdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alaksion.sneakerdex.domain.model.SneakersResponse
import com.alaksion.sneakerdex.domain.usecase.GetSneakerUseCase
import com.alaksion.sneakerdex.presentation.model.SneakerSizes
import com.alaksion.sneakerdex.shared.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SneakerDetailViewModel(
    application: Application,
    private val useCase: GetSneakerUseCase
): AndroidViewModel(application) {

    private val mSneakerInfo = MutableLiveData<Resource<SneakersResponse>>()
    var sneakerInfo : LiveData<Resource<SneakersResponse>> = mSneakerInfo

    private val mIsLoading = MutableLiveData<Boolean>()
    var isLoading : LiveData<Boolean> = mIsLoading

    private val mSelectedSize = MutableLiveData<SneakerSizes>()
    var selectedSize : LiveData<SneakerSizes> = mSelectedSize


    fun getSneaker(sneakerId: String) {
        mIsLoading.value = true

        viewModelScope.launch {
            val result = useCase.invoke(sneakerId).value

            withContext(Dispatchers.Main) {
                mSneakerInfo.value = result
            }
        }
    }

    fun selectSneakerSize(sneakerSizes: SneakerSizes){
        mSelectedSize.value = sneakerSizes
    }
}