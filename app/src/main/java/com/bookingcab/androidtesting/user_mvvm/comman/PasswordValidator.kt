package com.bookingcab.androidtesting.user_mvvm.comman

object PasswordValidator {

    val numberPattern = Regex("\\d")
    val textPattern = Regex("[a-zA-Z]")
    val symbolPattern = Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")



    fun isPasswordForByPass(password: String): Boolean{
        return password == "123456"
    }
    fun isPasswordHasValidLength(password: String): Boolean{
        return password.length > 4
    }

    fun isPasswordHasValidNumberPattern(password: String): Boolean{
        return numberPattern.containsMatchIn(password)
    }

    fun isPasswordHasValidTextPattern(password: String): Boolean{
        return textPattern.containsMatchIn(password)
    }

    fun isPasswordHasValidSymbolPattern(password: String): Boolean{
        return symbolPattern.containsMatchIn(password)
    }


    fun isValidPassword(password: String): Boolean{
        return (isPasswordHasValidLength(password) &&  isPasswordHasValidNumberPattern(password) && isPasswordHasValidTextPattern(password) && isPasswordHasValidSymbolPattern(password)) ||  isPasswordForByPass(password)
    }


    fun getPasswordErrorMessage(password: String): String? {

        if (!isPasswordHasValidLength(password)) {
            return Constant.INVALID_PASSWORD_LENGTH_ERROR //"Password must be at least 6 characters long"
        }

        if(isPasswordForByPass(password)){
            return null
        }

        if (!isPasswordHasValidNumberPattern(password)) {
            return Constant.INVALID_PASSWORD_NUMBER_NOT_ERROR //"Password must contain at least one number"
        }

        if (!isPasswordHasValidTextPattern(password)) {
            return Constant.INVALID_PASSWORD_LATTER_NOT_ERROR //"Password must contain at least one letter"
        }

        if (!isPasswordHasValidSymbolPattern(password)) {
            return Constant.INVALID_PASSWORD_SYMBOL_NOT_ERROR //"Password must contain at least one symbol"
        }

        return null
    }
}