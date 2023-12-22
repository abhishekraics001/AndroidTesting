package com.bookingcab.androidtesting.user_mvvm.domain.login.usecase

import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.bookingcab.androidtesting.user_mvvm.domain.login.repository.LoginRepository
import retrofit2.Response

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
                ApiResponse.Error("Failed", Exception("Unsuccessful response"))
            }
        } catch (e: Exception) {
            ApiResponse.Error("Error", e)
        }
        /*          try {
            val response = loginRepository.loginRequest(emailID,password)
            return ApiResponse.Success(response)
        } catch (e: Exception) {
              return ApiResponse.Error(e)
        }*/
    }


    override suspend fun execute(loginRequestData: LoginRequestData): ApiResponse<LoginResponseData> {
        return try {
            val response = loginRepository.loginRequest(loginRequestData)
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error("Failed", Exception("Unsuccessful response"))
            }
        } catch (e: Exception) {
            ApiResponse.Error("Error", e)
        }
    }
}