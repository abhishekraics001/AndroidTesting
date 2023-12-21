package com.bookingcab.androidtesting.login_mvvm.data.datasource

import com.bookingcab.androidtesting.login_mvvm.data.datasource.local.LocalDB
import com.bookingcab.androidtesting.login_mvvm.data.datasource.remote.ApiService
import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import retrofit2.Response

class DataSourceInterfaceImp(private val apiService: ApiService, private val localDB: LocalDB): DataSourceInterface {

    override suspend fun getCityDataFromLocal(userEmailID: String): Response<CityList> {
       return localDB.getDataFromLocalDB("");
       // return apiService.getCityList()
    }

    override suspend fun getDataFromRemoteAPi(
        userEmailID: String,
        userPassword: String
    ): Response<CityList> {
        return apiService.getCityList()
    }
}