package com.app.androidtesting.user_mvvm.domain.login.usecase

import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData

interface LoginUseCase {
    suspend fun execute(emailID: String, password: String) : ApiResponse<LoginResponseData>
    suspend fun execute(loginRequestData: LoginRequestData) : ApiResponse<LoginResponseData>
}