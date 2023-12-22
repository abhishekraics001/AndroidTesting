package com.bookingcab.androidtesting.user_mvvm.data.repository

import com.bookingcab.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.bookingcab.androidtesting.user_mvvm.domain.signup.repository.UserSignupRepository
import retrofit2.Response

class UserSignupRepositoryImp(private val dataSourceInterface: DataSourceInterface): UserSignupRepository {
    override suspend fun signupRequest(userSignupRequestData: UserSignupRequestData): Response<UserSignUpResponseData> {
        return dataSourceInterface.signupRequest(userSignupRequestData)
    }
}