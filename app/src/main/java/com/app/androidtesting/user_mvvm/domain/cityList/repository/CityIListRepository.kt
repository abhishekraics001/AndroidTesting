package com.app.androidtesting.user_mvvm.domain.cityList.repository

import com.app.androidtesting.user_mvvm.data.models.cityResponse.CityList
import retrofit2.Response

interface CityIListRepository {
    suspend fun getCityList(): Response<CityList>
}