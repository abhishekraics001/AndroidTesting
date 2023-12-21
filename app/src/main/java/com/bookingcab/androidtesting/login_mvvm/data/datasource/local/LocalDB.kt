package com.bookingcab.androidtesting.login_mvvm.data.datasource.local

import com.bookingcab.androidtesting.login_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.login_mvvm.data.models.cityResponse.Data
import retrofit2.Response

object LocalDB {

    fun getDataFromLocalDB(userEmailID: String): Response<CityList> {
        val datax: List<Data> = mutableListOf()
        val c = CityList(datax, "1")
        return Response.success(c);
    }
}