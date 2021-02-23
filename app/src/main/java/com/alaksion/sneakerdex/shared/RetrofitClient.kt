package com.alaksion.sneakerdex.shared

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private lateinit var retrofitClient: Retrofit
        private const val BASE_URL = "https://api.thesneakerdatabase.com"

        private fun getInstance(): Retrofit {
            if (!Companion::retrofitClient.isInitialized) {
                retrofitClient = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofitClient
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getInstance().create(serviceClass)
        }
    }
}