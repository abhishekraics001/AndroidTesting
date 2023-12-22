package com.bookingcab.androidtesting.user_mvvm.domain.cityList.usecase

import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.user_mvvm.domain.cityList.repository.CityIListRepository
import retrofit2.Response

class CityIListUseCaseImp (private val repository: CityIListRepository): CityListUseCase {

    override
    suspend fun execute(): Response<CityList> = repository.getCityList()

}