package com.bookingcab.androidtesting.user_mvvm.comman

object Constant {

    const val STATUS_SUCCESS = "SUCCESS"
    const val STATUS_ERROR = "ERROR"
    const val STATUS_FAILED = "FAILED"

    const val ALERT_TITLE = "Alert"

    const val INVALID_EMAIL_COMMON_MSG = "Please enter the valid email id with contains of (Min 6 char, @ and .com)"
    const val INVALID_EMAIL_EMPTY_ERROR = "Email should not be empty"
    const val INVALID_EMAIL_AMPERSAND_NOT_ERROR= "Email should contain @ symbol"
    const val INVALID_EMAIL_DOT_NOT_ERROR = "Email should contain a dot (.)"
    const val INVALID_EMAIL_FORMAT_ERROR  = "Invalid email format"

    const val INVALID_PASSWORD_COMMON_ERROR = "Please enter the valid password with contains of (Min 6 char, symbol and number)"
    const val INVALID_PASSWORD_LENGTH_ERROR = "Password must be at least 6 characters long"
    const val INVALID_PASSWORD_NUMBER_NOT_ERROR = "Password must contain at least one number"
    const val INVALID_PASSWORD_LATTER_NOT_ERROR = "Password must contain at least one letter"
    const val INVALID_PASSWORD_SYMBOL_NOT_ERROR = "Password must contain at least one symbol"
}