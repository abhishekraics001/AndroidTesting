package com.bookingcab.androidtesting.user_mvvm.comman

object EmailValidator {

    private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    fun getEmailErrorMessage(email: String): String? {
        return when {
            email.isBlank() -> Constant.INVALID_EMAIL_EMPTY_ERROR
            !email.contains("@") -> Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR
            !email.contains(".") -> Constant.INVALID_EMAIL_DOT_NOT_ERROR
            !isEmailValid(email) -> Constant.INVALID_EMAIL_FORMAT_ERROR
            else -> null
        }
    }
}