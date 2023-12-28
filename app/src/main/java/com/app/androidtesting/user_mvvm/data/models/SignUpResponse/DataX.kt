package com.app.androidtesting.user_mvvm.data.models.SignUpResponse

data class DataX(
    val email: String,
    val is_active: Int,
    val mobile: String,
    val signup_status: Int,
    val user_id: Int,
    val user_type_id: Int
)