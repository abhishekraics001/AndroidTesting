package com.bookingcab.androidtesting.user_mvvm.data.datasource

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val errorType: String, val exception: Exception) : ApiResponse<Nothing>()
}