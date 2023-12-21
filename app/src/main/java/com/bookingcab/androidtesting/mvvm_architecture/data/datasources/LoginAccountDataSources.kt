package com.bookingcab.mvvm_architecture.data.datasources

interface LoginAccountDataSources {
    suspend fun loginAccount(): Int

    suspend fun logoutAccount() : Int
}