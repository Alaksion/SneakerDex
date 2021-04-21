package com.alaksion.sneakerdex.core.network

import com.alaksion.sneakerdex.core.constants.SneakerDexConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private lateinit var retrofitClient: Retrofit
        private const val BASE_URL = "https://v1-sneakers.p.rapidapi.com"

        private fun makeHttpClient(): OkHttpClient.Builder {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader(SneakerDexConstants.API_AUTH_HEADER, SneakerDexConstants.API_KEY)
                    .build()
                chain.proceed(request)
            }
            return httpClient
        }


        private fun getInstance(): Retrofit {
            if (!Companion::retrofitClient.isInitialized) {
                retrofitClient = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(makeHttpClient().build())
                    .build()
            }

            return retrofitClient
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getInstance().create(serviceClass)
        }
    }
}