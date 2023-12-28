package com.app.androidtesting.user_mvvm.data.datasource

import com.app.androidtesting.user_mvvm.data.local.LocalDB
import com.app.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.app.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.app.androidtesting.user_mvvm.data.remote.ApiService
import com.app.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import retrofit2.Response

class DataSourceInterfaceImp(private val apiService: ApiService, private val localDB: LocalDB): DataSourceInterface {

    override suspend fun getCityDataFromLocal(): Response<CityList> {
        return localDB.getDataFromLocalDB("");
    }

    override suspend fun getDataFromRemoteAPi(): Response<CityList> {
        return apiService.getCityList()
    }

    override suspend fun loginRequest(
        emailID: String,
        password: String
    ): Response<LoginResponseData> {
        return apiService.loginUser(3, "test@gmail.com", "123456","Android","EC-2","4231fhgsa", true)
    }

    override suspend fun loginRequest(loginRequestData: LoginRequestData): Response<LoginResponseData> {
        return apiService.loginUser(loginRequestData)
    }

    override suspend fun signupRequest(userSignupRequestData: UserSignupRequestData): Response<UserSignUpResponseData> {
        return apiService.signupUser(userSignupRequestData)
    }


}