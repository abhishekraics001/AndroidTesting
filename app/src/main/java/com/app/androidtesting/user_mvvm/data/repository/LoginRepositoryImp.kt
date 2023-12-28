package com.app.androidtesting.user_mvvm.data.repository

import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.app.androidtesting.user_mvvm.domain.login.repository.LoginRepository
import retrofit2.Response

class LoginRepositoryImp(private val dataSourceInterface: DataSourceInterface): LoginRepository {
    override suspend fun loginRequest(
        emailID: String,
        password: String
    ): Response<LoginResponseData> {
        return dataSourceInterface.loginRequest(emailID, password)
    }

    override suspend fun loginRequest(loginRequestData: LoginRequestData): Response<LoginResponseData> {
        return dataSourceInterface.loginRequest(loginRequestData)
    }
}