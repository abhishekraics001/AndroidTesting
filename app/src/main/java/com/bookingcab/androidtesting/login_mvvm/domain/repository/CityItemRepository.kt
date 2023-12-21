package com.bookingcab.androidtesting.login_mvvm.domain.repository

import com.bookingcab.androidtesting.login_mvvm.data.models.cityResponse.CityList
import retrofit2.Response

interface CityItemRepository {
    suspend fun getCityItems(userEmailID: String, userPassword: String): Response<CityList>
}