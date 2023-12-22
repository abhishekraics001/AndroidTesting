package com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest

data class UserSignupRequestData(
    val company_id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val mobile: String,
    val mobile_prefix: String,
    val nationality: String,
    val password: String,
    val refer_by: String,
    val singup_status: String,
    val user_type_id: String
)