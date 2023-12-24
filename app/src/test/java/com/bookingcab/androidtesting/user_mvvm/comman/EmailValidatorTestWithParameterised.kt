package com.bookingcab.androidtesting.user_mvvm.comman

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized ::class)
class EmailValidatorTestWithParameterised(val userEmailID: String,  val expectedResultForValidEmail: Boolean, val expectedResultForErrorMsg: String?) {

    @Test
    fun emailValidationTest(){
        assertEquals(expectedResultForValidEmail, EmailValidator.isEmailValid(userEmailID))
    }

    @Test
    fun emailValidationErrorMsgTest(){
        assertEquals(expectedResultForErrorMsg, EmailValidator.getEmailErrorMessage(userEmailID))
    }


    companion object{
        @JvmStatic
        @Parameterized.Parameters
        fun emailValidationData(): Any? {
            return listOf(
                arrayOf("", false, Constant.INVALID_EMAIL_EMPTY_ERROR),
                arrayOf("test", false, Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR),
                arrayOf("test", false , Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR),
                arrayOf("test13", false, Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR),
                arrayOf("test13@", false , Constant.INVALID_EMAIL_DOT_NOT_ERROR),
                arrayOf("test13.com", false , Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR),
                arrayOf("test13@.", false, Constant.INVALID_EMAIL_FORMAT_ERROR),
                arrayOf("test13@.com", false, Constant.INVALID_EMAIL_FORMAT_ERROR),
                arrayOf("test13we@test.com", true, null),
               // arrayOf("test13we@test.com", false)

            )
        }
    }

}
