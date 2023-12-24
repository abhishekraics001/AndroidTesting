package com.bookingcab.androidtesting.user_mvvm.comman

object PhoneValidator {

    private const val PHONE_REGEX = "^\\+?[0-9. ()-]{10,25}$"

    fun isEmptyOrBlankPhoneNumber(phone: String): Boolean{
       return phone.isEmpty()
    }

    fun isValidLengthPhoneNumber(phone: String): Boolean{
        return phone.length in 10..12
    }

    fun isValidOrNot(phone: String): Boolean{
        return PHONE_REGEX.toRegex().matches(phone)
    }

     fun isPhoneValid(phone: String): Boolean {
        return (!isEmptyOrBlankPhoneNumber(phone) && isValidLengthPhoneNumber(phone) || isValidOrNot(phone))
    }


    fun getPhoneErrorMessage(phone: String): String? {
        return if(isEmptyOrBlankPhoneNumber(phone)){
            return Constant.INVALID_PHONE_NO_EMPTY_BLANK
        }else if(!isValidLengthPhoneNumber(phone)){
            return Constant.INVALID_PHONE_NO_LENGHTH
        }else if (isPhoneValid(phone)) {
            null
        } else {
            Constant.INVALID_PHONE_NO_ERROR
        }
    }
}