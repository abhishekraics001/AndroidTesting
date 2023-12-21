package com.bookingcab.androidtesting.login_mvvm.domain.repository

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import com.bookingcab.androidtesting.login_mvvm.data.models.ItemDataModel
import retrofit2.Response

interface CityItemRepository {
    suspend fun getCityItems(userEmailID: String, userPassword: String): Response<CityList>
}