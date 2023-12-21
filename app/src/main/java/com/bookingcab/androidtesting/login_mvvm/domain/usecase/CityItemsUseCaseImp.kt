package com.bookingcab.androidtesting.login_mvvm.domain.usecase

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import com.bookingcab.androidtesting.login_mvvm.domain.repository.CityItemRepository
import retrofit2.Response

class CityItemsUseCaseImp (private val repository: CityItemRepository): CityItemsUseCase {

    override
    suspend fun execute(userEmailID: String, userPassword: String): Response<CityList> = repository.getCityItems(userEmailID, userPassword)

}