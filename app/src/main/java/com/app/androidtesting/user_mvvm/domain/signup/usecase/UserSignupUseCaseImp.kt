package com.app.androidtesting.user_mvvm.domain.signup.usecase

import com.app.androidtesting.user_mvvm.comman.Constant.STATUS_ERROR
import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.app.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.app.androidtesting.user_mvvm.domain.signup.repository.UserSignupRepository

class UserSignupUseCaseImp(private val userSignupRepository: UserSignupRepository): UserSignupUseCase {

    override suspend fun UserSignupRequestData(userSignupRequestData: UserSignupRequestData): ApiResponse<UserSignUpResponseData> {
        return try {
            val response = userSignupRepository.signupRequest(userSignupRequestData)
            if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Error(STATUS_ERROR, Exception("Unsuccessful response"))
            }
        } catch (e: Exception) {
            ApiResponse.Error(STATUS_ERROR, e)
        }
    }
}