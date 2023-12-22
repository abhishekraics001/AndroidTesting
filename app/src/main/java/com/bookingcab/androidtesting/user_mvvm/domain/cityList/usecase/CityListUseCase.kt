package com.bookingcab.androidtesting.user_mvvm.domain.cityList.usecase

import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.CityList
import retrofit2.Response

interface CityListUseCase {

    suspend fun execute(): Response<CityList>
}