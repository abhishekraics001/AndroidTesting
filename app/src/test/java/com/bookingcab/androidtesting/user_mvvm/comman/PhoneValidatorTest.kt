package com.bookingcab.androidtesting.user_mvvm.comman

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class PhoneValidatorTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }


    @Test
    fun isEmptyOrBlankPhoneNumber() {
        val phoneNo = ""
        assertTrue(PhoneValidator.isEmptyOrBlankPhoneNumber(phoneNo))
        assertFalse(PhoneValidator.isValidOrNot(phoneNo))
        assertFalse(PhoneValidator.isPhoneValid(phoneNo))
        assertEquals(Constant.INVALID_PHONE_NO_EMPTY_BLANK, PhoneValidator.getPhoneErrorMessage(phoneNo))
    }

    @Test
    fun isValidLengthPhoneNumber() {
        var  phoneNo = "324762"
        assertFalse(PhoneValidator.isEmptyOrBlankPhoneNumber(phoneNo))
        assertFalse(PhoneValidator.isValidLengthPhoneNumber(phoneNo))
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH, PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "3247625475723234"
        assertFalse(PhoneValidator.isEmptyOrBlankPhoneNumber(phoneNo))
        assertFalse(PhoneValidator.isValidLengthPhoneNumber(phoneNo))
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH, PhoneValidator.getPhoneErrorMessage(phoneNo))


        phoneNo = "918130740896"
        assertFalse(PhoneValidator.isEmptyOrBlankPhoneNumber(phoneNo))
        assertTrue(PhoneValidator.isValidLengthPhoneNumber(phoneNo))
        assertTrue(PhoneValidator.isValidOrNot(phoneNo))
        assertTrue(PhoneValidator.isPhoneValid(phoneNo))
        assertNull(PhoneValidator.getPhoneErrorMessage(phoneNo))

    }

    @Test
    fun isValidOrNot() {
        var  phoneNo = "918130"
        assertFalse(PhoneValidator.isValidOrNot(phoneNo))
        assertFalse(PhoneValidator.isPhoneValid(phoneNo))
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH ,PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "918130740896666"
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH ,PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "918130740896"
        assertTrue(PhoneValidator.isValidOrNot(phoneNo))
        assertTrue(PhoneValidator.isPhoneValid(phoneNo))
        assertNull(PhoneValidator.getPhoneErrorMessage(phoneNo))
    }

    @Test
    fun getPhoneErrorMessage() {
        var phoneNo = ""
        assertEquals(Constant.INVALID_PHONE_NO_EMPTY_BLANK ,PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "918130"
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH ,PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "918130740896666"
        assertEquals(Constant.INVALID_PHONE_NO_LENGHTH ,PhoneValidator.getPhoneErrorMessage(phoneNo))

        phoneNo = "918130740896"
        assertTrue(PhoneValidator.isValidOrNot(phoneNo))
        assertTrue(PhoneValidator.isPhoneValid(phoneNo))
        assertNull(PhoneValidator.getPhoneErrorMessage(phoneNo))
    }
}