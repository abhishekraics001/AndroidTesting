package com.app.androidtesting.user_mvvm.data.local

import com.app.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.app.androidtesting.user_mvvm.data.models.cityResponse.Data
import retrofit2.Response

open class LocalDB {

    fun getDataFromLocalDB(userEmailID: String): Response<CityList> {
        val datax: List<Data> = mutableListOf()
        val c = CityList(datax, "1")
        return Response.success(c);
    }
}