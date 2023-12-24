package com.bookingcab.androidtesting.user_mvvm.comman

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class PasswordValidatorTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun isPasswordForPass() {
        val passwordByPass = "123456"
        assertEquals(true, PasswordValidator.isValidPassword(password = passwordByPass))

        assertNull(PasswordValidator.getPasswordErrorMessage(passwordByPass))

        assertEquals(true, PasswordValidator.isPasswordForByPass(passwordByPass))
    }

    @Test
    fun isPasswordHasLength() {
        var passwordByPass = "1234"
        assertFalse(PasswordValidator.isPasswordHasValidLength(passwordByPass))

         passwordByPass = "1234@45123jgds"
        assertTrue(PasswordValidator.isPasswordHasValidLength(passwordByPass))

    }

    @Test
    fun isPasswordHasLengthErrorMsg() {
        var password = "1234"
        assertFalse(PasswordValidator.isPasswordHasValidLength(password))
        assertEquals(Constant.INVALID_PASSWORD_LENGTH_ERROR, PasswordValidator.getPasswordErrorMessage(password))
        assertFalse(PasswordValidator.isValidPassword(password))

        password = "1234@45123jgds"
        assertTrue(PasswordValidator.isPasswordHasValidLength(password = password))
        assertTrue(PasswordValidator.isValidPassword(password))
        assertNull(PasswordValidator.getPasswordErrorMessage(password))
    }

    @Test
    fun isPasswordHasValidNumber(){
        var passwordByPass = "GHAFSH$#@$"
        assertFalse(PasswordValidator.isPasswordHasValidNumberPattern(passwordByPass))

        passwordByPass = "dgsdg1234$@"
        assertTrue(PasswordValidator.isPasswordHasValidNumberPattern(passwordByPass))

    }

    @Test
    fun isPasswordHasValidNumberErrorMsg() {
        var password = "Ahgfsadh@#$$%#@%"
        assertFalse(PasswordValidator.isPasswordHasValidNumberPattern(password))
        assertEquals(Constant.INVALID_PASSWORD_NUMBER_NOT_ERROR, PasswordValidator.getPasswordErrorMessage(password))
        assertFalse(PasswordValidator.isValidPassword(password))

        password = "1234@45123jgds"
        assertTrue(PasswordValidator.isPasswordHasValidNumberPattern(password = password))
        assertTrue(PasswordValidator.isValidPassword(password))
        assertNull(PasswordValidator.getPasswordErrorMessage(password))
    }



    @Test
    fun isPasswordHasValidText(){
        var passwordByPass = "1234"
        assertFalse(PasswordValidator.isPasswordHasValidTextPattern(passwordByPass))
        passwordByPass = "dgsdg1234$@"
        assertTrue(PasswordValidator.isPasswordHasValidTextPattern(passwordByPass))
    }


    @Test
    fun isPasswordHasValidTextErrorMsg() {
        var password = "213@##@%."
        assertFalse(PasswordValidator.isPasswordHasValidTextPattern(password))
        assertEquals(Constant.INVALID_PASSWORD_LATTER_NOT_ERROR, PasswordValidator.getPasswordErrorMessage(password))
        assertFalse(PasswordValidator.isValidPassword(password))

        password = "1234@45123jgds"
        assertTrue(PasswordValidator.isPasswordHasValidTextPattern(password = password))
        assertTrue(PasswordValidator.isValidPassword(password))
        assertNull(PasswordValidator.getPasswordErrorMessage(password))
    }


    @Test
    fun isPasswordHasSymbol(){
        var passwordByPass = "1234"
        assertFalse(PasswordValidator.isPasswordHasValidSymbolPattern(passwordByPass))

        passwordByPass = "dgsdg1234@"
        assertTrue(PasswordValidator.isPasswordHasValidSymbolPattern(passwordByPass))
    }

    @Test
    fun isPasswordHasSymbolErrorMsg() {
        var password = "ffadg1312com"
        assertFalse(PasswordValidator.isPasswordHasValidSymbolPattern(password))
        assertEquals(Constant.INVALID_PASSWORD_SYMBOL_NOT_ERROR, PasswordValidator.getPasswordErrorMessage(password))
        assertFalse(PasswordValidator.isValidPassword(password))

        password = "1234@45123jgds"
        assertTrue(PasswordValidator.isPasswordHasValidSymbolPattern(password = password))
        assertTrue(PasswordValidator.isValidPassword(password))
        assertNull(PasswordValidator.getPasswordErrorMessage(password))
    }


}