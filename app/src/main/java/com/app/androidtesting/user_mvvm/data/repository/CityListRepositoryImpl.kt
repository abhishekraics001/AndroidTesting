package com.app.androidtesting.user_mvvm.data.repository

import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.app.androidtesting.user_mvvm.domain.cityList.repository.CityIListRepository
import retrofit2.Response

class CityListRepositoryImpl(private val dataSourceInterface: DataSourceInterface) :  CityIListRepository {

    private val isInternet = true;
    override suspend fun getCityList(): Response<CityList> {
        return if(isInternet) {
            dataSourceInterface.getDataFromRemoteAPi()
        }else{
            dataSourceInterface.getCityDataFromLocal()
        }
    }
}

