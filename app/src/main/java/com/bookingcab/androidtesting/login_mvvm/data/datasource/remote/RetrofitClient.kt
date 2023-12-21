package com.bookingcab.androidtesting.login_mvvm.data.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  RetrofitClient {

    companion object {
        private const val BASE_URL = "http://demob2b.bookingcabs.com:3002"

        fun createWebService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}