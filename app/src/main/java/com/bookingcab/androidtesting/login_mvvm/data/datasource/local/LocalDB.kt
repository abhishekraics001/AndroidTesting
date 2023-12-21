package com.bookingcab.androidtesting.login_mvvm.data.datasource.local

import com.bookingcab.androidtesting.login_mvvm.data.models.CityList
import com.bookingcab.androidtesting.login_mvvm.data.models.Data
import retrofit2.Response

object LocalDB {


    fun getDataFromLocalDB(userEmailID: String): Response<CityList> {
        val datax: List<Data> = mutableListOf()
        val c = CityList(datax, "1")
        //val data: Response<CityList> = c;
        return Response.success(c);
    }
}