package com.app.androidtesting.user_mvvm.domain.signup.usecase

import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.app.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData

interface UserSignupUseCase {

    suspend fun UserSignupRequestData(ax: UserSignupRequestData): ApiResponse<UserSignUpResponseData>
}