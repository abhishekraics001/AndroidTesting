package com.app.androidtesting.user_mvvm.comman

object EmailValidator {

    private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"

    fun isBlanckEmpty(email: String): Boolean{
        return email.isBlank()
    }

    fun isContainsDot(email: String): Boolean{
        return email.contains(".")
    }


    fun isContainsAmpersand (email: String): Boolean{
        return email.contains("@")
    }

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email)
    }


    fun getEmailErrorMessage(email: String): String? {
        return when {
            isBlanckEmpty(email) -> Constant.INVALID_EMAIL_EMPTY_ERROR
            !isContainsAmpersand(email) -> Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR
            !isContainsDot(email) -> Constant.INVALID_EMAIL_DOT_NOT_ERROR
            !isEmailValid(email) -> Constant.INVALID_EMAIL_FORMAT_ERROR
            else -> null
        }
    }
}