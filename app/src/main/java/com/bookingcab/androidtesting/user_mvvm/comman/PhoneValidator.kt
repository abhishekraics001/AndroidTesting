package com.bookingcab.androidtesting.user_mvvm.comman

object PhoneValidator {

    private const val PHONE_REGEX = "^\\+?[0-9. ()-]{10,25}$"

    /*fun validatePhone(phone: String): Boolean {
        val error  = getPhoneErrorMessage(phone)
        return error == null
    }*/

    private fun isPhoneValid(phone: String): Boolean {
        return PHONE_REGEX.toRegex().matches(phone)
    }

    fun getPhoneErrorMessage(phone: String): String? {
        return if (isPhoneValid(phone)) {
            null
        } else {
            "Invalid phone number"
        }
    }
}