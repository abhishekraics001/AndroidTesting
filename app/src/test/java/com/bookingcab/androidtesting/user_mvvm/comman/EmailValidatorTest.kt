package com.bookingcab.androidtesting.user_mvvm.comman

import org.junit.Test
import org.junit.Assert.*

class EmailValidatorTest {

    @Test
    fun isValidEmailTest() {
        val emailID = "test@example.com"
        assertFalse(EmailValidator.isBlanckEmpty(emailID))
        assertTrue(EmailValidator.isContainsAmpersand(emailID))
        assertTrue(EmailValidator.isContainsDot(emailID))
        assertTrue(EmailValidator.isEmailValid(emailID))
        assertNull(EmailValidator.getEmailErrorMessage(emailID))
    }


    @Test
    fun isBlankEmptyEmailID(){
        val emailID = ""
        assertTrue(EmailValidator.isBlanckEmpty(emailID))
        assertFalse(EmailValidator.isContainsDot(emailID))
        assertFalse(EmailValidator.isContainsAmpersand(emailID))
        assertFalse(EmailValidator.isEmailValid(emailID))
        assertEquals(Constant.INVALID_EMAIL_EMPTY_ERROR, EmailValidator.getEmailErrorMessage(emailID))
    }




    @Test
    fun isEmailContainsValidAmpersandSymbolTest() {
        val emailID = "testexample.com"
        assertFalse(EmailValidator.isContainsAmpersand(emailID))
        assertFalse(EmailValidator.isEmailValid(emailID))
        assertEquals(Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR, EmailValidator.getEmailErrorMessage(emailID))
    }

    @Test
    fun emailWithoutDotTest() {
        val emailID = "test@examplecom"
        assertFalse(EmailValidator.isContainsDot(emailID))
        assertFalse(EmailValidator.isEmailValid(emailID))
        assertEquals(Constant.INVALID_EMAIL_DOT_NOT_ERROR, EmailValidator.getEmailErrorMessage(emailID))
    }

    @Test
    fun invalidEmailFormatTest() {
        val emailID = "test@.com"
        assertFalse(EmailValidator.isEmailValid(emailID))
        assertEquals(Constant.INVALID_EMAIL_FORMAT_ERROR, EmailValidator.getEmailErrorMessage(emailID))
    }


}
