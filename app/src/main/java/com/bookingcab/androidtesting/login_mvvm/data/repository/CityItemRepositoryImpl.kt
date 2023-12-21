package com.bookingcab.androidtesting.login_mvvm.data.repository

import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.login_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.login_mvvm.domain.repository.CityItemRepository
import retrofit2.Response

class CityItemRepositoryImpl(private val dataSourceInterface: DataSourceInterface) : CityItemRepository {

    private val isInternet = true;
    override suspend fun getCityItems(userEmailID: String, userPassword: String): Response<CityList> {
        return if(isInternet) {
            dataSourceInterface.getDataFromRemoteAPi(userEmailID, userPassword)
        }else{
            dataSourceInterface.getCityDataFromLocal(userEmailID)
        }
    }
}

