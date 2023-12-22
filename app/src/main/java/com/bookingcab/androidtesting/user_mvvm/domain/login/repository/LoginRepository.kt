package com.bookingcab.androidtesting.user_mvvm.domain.login.repository

import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import retrofit2.Response

interface LoginRepository {

    suspend fun loginRequest(emailID: String, password: String): Response<LoginResponseData>

    suspend fun loginRequest(loginRequestData: LoginRequestData): Response<LoginResponseData>

}