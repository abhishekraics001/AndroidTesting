package com.app.androidtesting.user_mvvm.data.models.loginRequest

data class LoginRequestData(
    val user_type_id: Int,
    val username: String,
    val password: String,
    val callfrom: String,
    val login_location: String,
    val gcm_id: String,
    val sms_send_status: Boolean
)