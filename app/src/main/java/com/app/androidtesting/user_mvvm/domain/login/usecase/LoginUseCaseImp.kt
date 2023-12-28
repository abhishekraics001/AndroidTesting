package com.app.androidtesting.user_mvvm.domain.login.usecase

import com.app.androidtesting.user_mvvm.comman.Constant.STATUS_ERROR
import com.app.androidtesting.user_mvvm.comman.Constant.STATUS_FAILED
import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.app.androidtesting.user_mvvm.domain.login.repository.LoginRepository

class LoginUseCaseImp(private val loginRepository: LoginRepository): LoginUseCase {

    override suspend fun execute(
        emailID: String,
        password: String
    ): ApiResponse<LoginResponseData> {
        return try {
            val response = loginRepository.loginRequest(emailID, password)
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error(STATUS_FAILED, Exception("Unsuccessful response"))
            }
        } catch (e: Exception) {
            ApiResponse.Error(STATUS_ERROR, e)
        }
    }


    override suspend fun execute(loginRequestData: LoginRequestData): ApiResponse<LoginResponseData> {
        return try {
            val response = loginRepository.loginRequest(loginRequestData)
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error("$STATUS_FAILED", Exception("Unsuccessful response"))
            }
        } catch (e: Exception) {
            ApiResponse.Error("$STATUS_ERROR", e)
        }
    }
}