package com.bookingcab.androidtesting.user_mvvm.data.datasource

import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import retrofit2.Response

interface DataSourceInterface {
    suspend fun getCityDataFromLocal(): Response<CityList>
    suspend fun getDataFromRemoteAPi(): Response<CityList>


    suspend fun loginRequest(emailID: String,password: String): Response<LoginResponseData>
    suspend fun loginRequest(loginRequestData: LoginRequestData): Response<LoginResponseData>

    suspend fun signupRequest(userSignupRequestData: UserSignupRequestData) : Response<UserSignUpResponseData>
}