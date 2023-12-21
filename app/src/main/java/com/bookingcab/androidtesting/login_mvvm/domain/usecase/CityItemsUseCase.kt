package com.bookingcab.androidtesting.login_mvvm.domain.usecase

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import retrofit2.Response

interface CityItemsUseCase {

    suspend fun execute(userEmailID: String, userPassword: String): Response<CityList>
}