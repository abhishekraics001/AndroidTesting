package com.bookingcab.androidtesting.user_mvvm.comman

object PasswordValidator {

    fun getPasswordErrorMessage(password: String): String? {
        val numberPattern = Regex("\\d")
        val textPattern = Regex("[a-zA-Z]")
        val symbolPattern = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")

        val hasNumber = numberPattern.containsMatchIn(password)
        val hasText = textPattern.containsMatchIn(password)
        val hasSymbol = symbolPattern.containsMatchIn(password)

        if (password.length < 5) {
            return "Password must be at least 6 characters long"
        }

    /*    if (!hasNumber) {
            return "Password must contain at least one number"
        }

        if (!hasText) {
            return "Password must contain at least one letter"
        }

        if (!hasSymbol) {
            return "Password must contain at least one symbol"
        }*/

        return null
    }
}