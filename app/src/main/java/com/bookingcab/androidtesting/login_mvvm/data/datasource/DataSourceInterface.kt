package com.bookingcab.androidtesting.login_mvvm.data.datasource

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import retrofit2.Response

interface DataSourceInterface {
    suspend fun getCityDataFromLocal(userEmailID: String): Response<CityList>;
    suspend fun getDataFromRemoteAPi(userEmailID: String, userPassword: String): Response<CityList>;
}