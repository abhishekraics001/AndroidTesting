package com.app.androidtesting.user_mvvm.data.repository

import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.app.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.app.androidtesting.user_mvvm.domain.signup.repository.UserSignupRepository
import retrofit2.Response

class UserSignupRepositoryImp(private val dataSourceInterface: DataSourceInterface): UserSignupRepository {
    override suspend fun signupRequest(userSignupRequestData: UserSignupRequestData): Response<UserSignUpResponseData> {
        return dataSourceInterface.signupRequest(userSignupRequestData)
    }
}