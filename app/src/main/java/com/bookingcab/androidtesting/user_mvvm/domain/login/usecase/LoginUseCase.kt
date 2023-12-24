package com.bookingcab.androidtesting.user_mvvm.domain.login.usecase

import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData

interface LoginUseCase {
    suspend fun execute(emailID: String, password: String) : ApiResponse<LoginResponseData>
    suspend fun execute(loginRequestData: LoginRequestData) : ApiResponse<LoginResponseData>
}