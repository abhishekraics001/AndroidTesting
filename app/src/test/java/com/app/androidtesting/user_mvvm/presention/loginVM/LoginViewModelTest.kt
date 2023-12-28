package com.app.androidtesting.user_mvvm.presention.loginVM

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.app.androidtesting.user_mvvm.di.LoginKoinModule.LoginKoinModules
import com.app.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class LoginViewModelTest  {

    // Rule to make LiveData work synchronously in tests
    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    // Rule to enable Mockito annotations
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()


    // Mocking the Observer
    @Mock
    lateinit var observer: Observer<String>


    //Create Standard Dispatchers
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        //Start or load the KOIn module
        startKoin { modules(LoginKoinModules) }

        //Set Dispatchers with Main thread
        Dispatchers.setMain(testDispatcher)
    }





    // Add more test cases as needed




    @After
    fun tearDown() {
        //reset Dispatchers
        Dispatchers.resetMain()

        // Stop Koin after the test
        stopKoin()
    }




    @Test
    fun `validatePassword returns true for a valid password`() = runTest{
        val viewModel = LoginViewModel(mockk(relaxed = true))
        val result = viewModel.validatePassword("ValidPassword123!")

        assert(result)
        assert(viewModel.passwordError.value == null)
    }



    @Test
    fun `validatePassword returns false and sets error for an invalid password`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        val result = viewModel.validatePassword("short")

        Assert.assertFalse(result)
        assert(viewModel.passwordError.value != null)
    }

    @Test
    fun `validatePassword returns false and sets error for an invalid password with `() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        val result = viewModel.validatePassword("short")

        Assert.assertFalse(result)
        assert(viewModel.passwordError.value != null)



    }





    @Test
    fun `validateEmail returns true for a valid email`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        val result = viewModel.validateEmail("test@example.com")

        assert(result)
        assert(viewModel.emailError.value == null)
    }


    @Test
    fun `validateEmail returns false and sets error for an invalid email`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        val result = viewModel.validateEmail("invalid_email")

        assert(!result)
        assert(viewModel.emailError.value != null)
    }



    @Test
    fun `isValidCredentials returns true for valid email and password`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@example.com"
        viewModel.userPassword.value = "ValidPassword123!"

        val result = viewModel.isValidCredentials()

        assert(result)
        assert(viewModel.loginResponseStatus.value == null)
    }

    @Test
    fun `isValidCredentials returns false and sets error for invalid email`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "invalid_email"
        viewModel.userPassword.value = "ValidPassword123"

        val result = viewModel.isValidCredentials()

        assert(!result)
        assert(viewModel.loginResponseStatus.value != null)
    }



    @Test
    fun `isValidCredentials returns false and sets error for invalid password 2`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@example.com"
        viewModel.userPassword.value = "12345"

        val result = viewModel.isValidCredentials()
        Assert.assertFalse(result)
        assert(viewModel.loginResponseStatus.value != null)
    }





    @Test
    fun `onLoginButtonClicked calls loginuserWithBody when credentials are valid`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "123456"

       // every { viewModel.isValidCredentials() } returns true
        val result = viewModel.isValidCredentials()
        assert(result)


        viewModel.onLoginButtonClicked()

        // Add assertions or verifications as needed
    }

    @Test
    fun `onLoginButtonClicked calls loginuserWithBody when credentials are valid 2`() = runBlocking {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "123456"
        runTest {

             viewModel.loginuserWithBody("test@gmail.com", "123456")

            //assert(result)
        }
    }




    @Test
    fun `onLoginButtonClicked does not call loginuserWithBody when credentials are invalid`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        viewModel.userEmailID.value = "test@gmail"
        viewModel.userPassword.value = "1234"

        //every { viewModel.isValidCredentials() } returns false
        val result = viewModel.isValidCredentials()
        Assert.assertFalse(result)

        viewModel.onLoginButtonClicked()

        // Add assertions or verifications as needed
    }




}

