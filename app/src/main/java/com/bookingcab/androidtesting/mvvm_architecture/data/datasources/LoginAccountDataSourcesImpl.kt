package com.bookingcab.androidtesting.mvvm_architecture.data.datasources

import com.bookingcab.mvvm_architecture.data.datasources.LoginAccountDataSources

class LoginAccountDataSourcesImpl: LoginAccountDataSources {
    override suspend fun loginAccount(): Int {
        return loginAPICall();
    }

    override suspend fun logoutAccount(): Int {
        return  0
    }




    private fun loginAPICall(): Int{
        return 1
    }

}