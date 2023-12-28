package com.app.androidtesting.user_mvvm.domain.cityList.usecase

import com.app.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.app.androidtesting.user_mvvm.domain.cityList.repository.CityIListRepository
import retrofit2.Response

class CityIListUseCaseImp (private val repository: CityIListRepository): CityListUseCase {

    override
    suspend fun execute(): Response<CityList> = repository.getCityList()

}