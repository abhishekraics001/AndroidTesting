package com.bookingcab.androidtesting.user_mvvm.comman

object EmailValidator {

    private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }

    fun getEmailErrorMessage(email: String): String? {
        return when {
            email.isBlank() -> "Email should not be empty"
            !email.contains("@") -> "Email should contain @ symbol"
            !email.contains(".") -> "Email should contain a dot (.)"
            !isEmailValid(email) -> "Invalid email format"
            else -> null
        }
    }
}