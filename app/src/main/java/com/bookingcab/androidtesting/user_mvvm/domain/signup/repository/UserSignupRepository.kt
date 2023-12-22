package com.bookingcab.androidtesting.user_mvvm.domain.signup.repository

import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import retrofit2.Response

interface UserSignupRepository {
    suspend fun signupRequest(userSignupRequestData: UserSignupRequestData): Response<UserSignUpResponseData>
}