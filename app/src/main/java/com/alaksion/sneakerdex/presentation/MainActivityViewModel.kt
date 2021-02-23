package com.alaksion.sneakerdex.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alaksion.sneakerdex.data.model.SneakerData
import com.alaksion.sneakerdex.data.model.SneakerResponseData
import com.alaksion.sneakerdex.data.repository.request.GetSneakersRequestParams
import com.alaksion.sneakerdex.domain.usecase.GetSneakersListUseCase
import com.alaksion.sneakerdex.shared.listeners.ApiListener

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val getSneakersUseCase = GetSneakersListUseCase()

    fun getSneakers(){
        val requestParams = GetSneakersRequestParams(
            "10",
            "1",
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
                val list = response
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }

        }, requestParams)
    }
}