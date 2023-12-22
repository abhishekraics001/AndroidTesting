package com.bookingcab.androidtesting.user_mvvm.domain.signup.usecase

import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData

interface UserSignupUseCase {

    suspend fun UserSignupRequestData(ax: UserSignupRequestData): ApiResponse<UserSignUpResponseData>
}