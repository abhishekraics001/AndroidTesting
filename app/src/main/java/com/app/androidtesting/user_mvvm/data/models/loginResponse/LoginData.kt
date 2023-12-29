package com.app.androidtesting.user_mvvm.data.models.loginResponse

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity(tableName = "LoginData")
data class LoginData(

    //@PrimaryKey(autoGenerate = true)
    //val id: Long = 0,

    //val accept_fare: Int,
   // val company_id: Int,
   // val country_id: Int,
    val email: String,
    val first_name: String,
    val mobile: String,
  /*  val is_active: Int,
    val last_name: String,
    val login_otp_status: Int,

    val mobile_prefix: String,
    val signup_status: Int,
    val token: String,
    val user_grade: String,
    val user_id: Int,
    val user_type_id: Int*/
)