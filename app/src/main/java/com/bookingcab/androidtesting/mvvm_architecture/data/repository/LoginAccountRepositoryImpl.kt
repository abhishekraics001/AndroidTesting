package com.bookingcab.mvvm_architecture.data.repository

import com.bookingcab.mvvm_architecture.data.datasources.LoginAccountDataSources
import com.bookingcab.mvvm_architecture.domain.repository.LoginAccountRepository

class LoginAccountRepositoryImpl(private val loginAccountDataSources: LoginAccountDataSources) :
    LoginAccountRepository {

    override suspend fun loginAccount(): Int = loginAccountDataSources.loginAccount()

    override suspend fun logOutAccount(): Int {
        return loginAccountDataSources.logoutAccount()
    }
}