package com.bookingcab.androidtesting.login_mvvm.data.datasource.remote

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import com.bookingcab.androidtesting.login_mvvm.data.models.ItemDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// data/UserService.kt
interface ApiService {

    //https://api.bookingcabs.com:3001/api/v1/vehicle-type
    //@GET("users/{userId}")
    @GET("/api/v1/vehicle-type")
    suspend fun getCityList(@Path("userEmailId") userEmailId: String, @Path("userPassword") userPassword: String): CityList

    @GET("/api/v1/vehicle-type")
    suspend fun getCityList(): Response<CityList>
}