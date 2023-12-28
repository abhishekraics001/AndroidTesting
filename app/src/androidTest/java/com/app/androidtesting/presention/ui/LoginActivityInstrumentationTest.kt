package com.app.androidtesting.presention.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.androidtesting.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.app.androidtesting.user_mvvm.presentation.ui.LoginActivity


import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityInstrumentationTest {

    private lateinit var activityScenario: ActivityScenario<LoginActivity>

    /*@BeforeClass
    fun setUpActivity(){

    }

    @AfterClass
    fun CleaupActivity(){

    }*/

    @Before
    fun setUp() {
        // Launch the LoginActivity before each test
        //LoginKoinModule.inject()
        activityScenario = ActivityScenario.launch(LoginActivity::class.java)

    }

    @After
    fun tearDown() {
        // Close the activity after each test
        activityScenario.close()
    }


    @Test
    fun testLoginButtonClicked_Without_Credentils() {
        //1st click on login button to check validation
        Thread.sleep(1000)
        onView(withId(R.id.loginBtn)).perform(click())
        Thread.sleep(3000)
    }


    @Test
    fun testLoginButtonClickedWithValidCredentials() {
        Thread.sleep(1000)
        // Type valid email and password
        onView(withId(R.id.userLoginEmailID)).perform(replaceText("test@gmail.com"))
        onView(withId(R.id.userLoginPassword)).perform(replaceText("123456"))


        // Click the login button
        onView(withId(R.id.loginBtn)).perform(click())
       Thread.sleep(3000)

    }

    @Test
    fun testLoginButtonClickedWithInvalidCredentials() {
        Thread.sleep(1000)
        // Type valid email and password
        onView(withId(R.id.userLoginEmailID)).perform(replaceText("test@example.com"))
        onView(withId(R.id.userLoginPassword)).perform(replaceText("password123"))


        // Click the login button
        onView(withId(R.id.loginBtn)).perform(click())
        Thread.sleep(3000)

    }



    @Test
    fun clickOnSignupButton(){
        Thread.sleep(1000)
        onView(withId(R.id.signupButton)).perform(click())
        Thread.sleep(1000)
    }


    @Test
    fun clickOnForgotPasswordButton(){
        Thread.sleep(1000)
        onView(withId(R.id.forgotPassword)).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun clickOnForgotTextButtonText(){
        Thread.sleep(1000)
        onView(withText("Login")).perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun clickOnForgotSignupButtonText(){
        Thread.sleep(1000)
        onView(withText("Signup")).perform(click())
        Thread.sleep(1000)
    }
    @Test
    fun clickOnForgotForgotPassordButtonText(){
        Thread.sleep(1000)
        onView(withText("Forgot Password")).perform(click())
        Thread.sleep(1000)
    }
}

