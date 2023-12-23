package com.bookingcab.androidtesting.user_mvvm.comman

import org.junit.Test
import org.junit.Assert.*

class EmailValidatorTest {

    @Test
    fun validEmailTest() {
        assertTrue(EmailValidator.isEmailValid("test@example.com"))
        assertNull(EmailValidator.getEmailErrorMessage("test@example.com"))
    }

    @Test
    fun emptyEmailTest() {
        assertFalse(EmailValidator.isEmailValid(""))
        assertEquals("Email should not be empty", EmailValidator.getEmailErrorMessage(""))
    }

    @Test
    fun emailWithoutAtSymbolTest() {
        assertFalse(EmailValidator.isEmailValid("testexample.com"))
        assertEquals("Email should contain @ symbol", EmailValidator.getEmailErrorMessage("testexample.com"))
    }

    @Test
    fun emailWithoutDotTest() {
        assertFalse(EmailValidator.isEmailValid("test@examplecom"))
        assertEquals("Email should contain a dot (.)", EmailValidator.getEmailErrorMessage("test@examplecom"))
    }

    @Test
    fun invalidEmailFormatTest() {
        assertFalse(EmailValidator.isEmailValid("test@.com"))
        assertEquals("Invalid email format", EmailValidator.getEmailErrorMessage("test@.com"))
    }

    /*@Test
    fun nullEmailTest() {
        assertFalse(EmailValidator.isEmailValid(null))
        assertEquals("Email should not be empty", EmailValidator.getEmailErrorMessage(null))
    }*/

    // Add more test cases as needed to cover other scenarios
}
