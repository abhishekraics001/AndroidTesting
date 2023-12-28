package com.app.mvvm_architecture.domain.repository

interface LoginAccountRepository {
    suspend fun loginAccount() : Int
    suspend fun logOutAccount(): Int
}