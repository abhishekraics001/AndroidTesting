package com.bookingcab.androidtesting.login_mvvm.data.models.loginResponse

data class Data(
    val accept_fare: Int,
    val company_id: Int,
    val country_id: Int,
    val email: String,
    val first_name: String,
    val is_active: Int,
    val last_name: String,
    val login_otp_status: Int,
    val mobile: String,
    val mobile_prefix: String,
    val signup_status: Int,
    val token: String,
    val user_grade: String,
    val user_id: Int,
    val user_type_id: Int
)