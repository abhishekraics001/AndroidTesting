package com.bookingcab.androidtesting.login_mvvm.data.repository

import android.content.Context
import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.login_mvvm.data.datasource.local.LocalDB
import com.bookingcab.androidtesting.login_mvvm.data.datasource.remote.ApiService
import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import com.bookingcab.androidtesting.login_mvvm.domain.repository.CityItemRepository
import retrofit2.Response

/*
class CityItemRepositoryImpl(private val apiService: ApiService) : CityItemRepository {

    override suspend fun getCityItems(userEmailID: String, userPassword: String): CityList {
        return apiService.getCityList(userEmailID, userPassword);
    }
}*/

class CityItemRepositoryImpl(private val dataSourceInterface: DataSourceInterface) : CityItemRepository {

    private val isInternet = false;
    override suspend fun getCityItems(userEmailID: String, userPassword: String): Response<CityList> {
       /* return if(isInternet) {
            getDataFromRemoteAPi(userEmailID, userPassword)
        }else{
            getCityDataFromLocal(userEmailID)
        }*/
        //return apiService.getCityList(userEmailID, userPassword)
        //return apiService.getCityList()


        return if(isInternet) {
            dataSourceInterface.getDataFromRemoteAPi(userEmailID, userPassword)
        }else{
            dataSourceInterface.getCityDataFromLocal(userEmailID)
        }
       // return dataSourceInterface.getDataFromRemoteAPi(userEmailID, userPassword)

    }

    /*override suspend fun getDataFromRemoteAPi(userEmailID: String, userPassword: String): Response<CityList> {
        return dataSourceInterface.getDataFromRemoteAPi("", "")
    }

    override suspend fun getCityDataFromLocal(userEmailID: String): Response<CityList> {
       // return LocalDB.getDataFromLocalDB(userEmailID);
        return dataSourceInterface.getCityDataFromLocal("")
    }*/
}

