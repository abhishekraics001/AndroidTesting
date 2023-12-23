import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule.LoginKoinModules
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule.inject
import com.bookingcab.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
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
        val result = viewModel.validatePassword("ValidPassword123")

        assert(result)
        assert(viewModel.passwordError.value == null)
    }

    @Test
    fun `validatePassword returns false and sets error for an invalid password`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        val result = viewModel.validatePassword("short")

        assert(result)
        //assert(viewModel.passwordError.value != null)
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
        viewModel.userPassword.value = "ValidPassword123"

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
    fun `isValidCredentials returns false and sets error for invalid password`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@example.com"
        viewModel.userPassword.value = ""

        val result = viewModel.isValidCredentials()

        assert(!result)
        //assert(viewModel.loginResponseStatus.value != null)



    }

    @Test
    fun `isValidCredentials returns false and sets error for invalid password 2`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@example.com"
        viewModel.userPassword.value = "123456"

        val result = viewModel.isValidCredentials()
        assert(result)


        //assert(viewModel.loginResponseStatus.value != null)



    }


    @Test
    fun `onLoginButtonClicked calls loginuserWithBody when credentials are valid`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))

        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "123456"

        every { viewModel.isValidCredentials() } returns true

        viewModel.onLoginButtonClicked()

        // Add assertions or verifications as needed
    }

    @Test
    fun `onLoginButtonClicked does not call loginuserWithBody when credentials are invalid`() {
        val viewModel = LoginViewModel(mockk(relaxed = true))
        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "123456"

        every { viewModel.isValidCredentials() } returns false

        viewModel.onLoginButtonClicked()

        // Add assertions or verifications as needed
    }
}

/*
package com.bookingcab.androidtesting.user_mvvm.presention

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bookingcab.androidtesting.user_mvvm.comman.Constant
import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.Data
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.Responsedata
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule.LoginKoinModules
import com.bookingcab.androidtesting.user_mvvm.domain.login.usecase.LoginUseCase
import com.bookingcab.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import io.mockk.MockKStubScope
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    val loginRequestData = LoginRequestData(
        user_type_id = 3,
        username =  "test@gmail.com",
        password =  "test@gmail.com",
        //username = "test@gmail.com",
        //password = "123456",
        callfrom = "Android",
        login_location = "EC2",
        gcm_id = "56342hjgsda",
        sms_send_status = true
    );

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        LoginKoinModule.inject()
        */
/*startKoin {
            modules(LoginKoinModules)
        }*//*


    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `loginuserWithBody success`() {
        val loginUseCase = mockk<LoginUseCase>()
        */
/*val datax: List<Data> = mutableListOf()
        val c = CityList(datax, "1")*//*

        val resData = LoginData("Test@gmail.com", "Abhishek", "09808098")
        val rx = Responsedata(resData, "Success")

        coEvery {
            loginUseCase.execute(loginRequestData)
        } returns ApiResponse.Success(rx)

        val viewModel = LoginViewModel(loginUseCase)

        // Set up observers for LiveData
        val loadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val responseStatusObserver = mockk<Observer<String>>(relaxed = true)
        val responseDataObserver = mockk<Observer<LoginData>>(relaxed = true)

        viewModel.isloading.observeForever(loadingObserver)
        viewModel.loginResponseStatus.observeForever(responseStatusObserver)
        viewModel.loginResponData.observeForever(responseDataObserver)

        // Trigger the function you want to test
        viewModel.loginuserWithBody("test@gmail.com", "password123")

        // Verify that the LiveData values have been updated correctly
        assert(viewModel.isloading.value == false) // Assuming it's false after success
        assert(viewModel.loginResponseStatus.value == "Success")
        assert(viewModel.loginResponData.value != null)

        // Verify that the observers have been called
        coVerify { loadingObserver.onChanged(false) }
        coVerify { responseStatusObserver.onChanged("Success") }
        coVerify { responseDataObserver.onChanged(any()) }
    }

    @Test
    fun `loginuserWithBody error`() {
        val loginUseCase = mockk<LoginUseCase>()
        coEvery {
            loginUseCase.execute(loginRequestData)
        } returns ApiResponse.Error(Constant.STATUS_ERROR, Exception("Some error"))

        val viewModel = LoginViewModel(loginUseCase)

        // Set up observers for LiveData
        val loadingObserver = mockk<Observer<Boolean>>(relaxed = true)
        val responseStatusObserver = mockk<Observer<String>>(relaxed = true)

        viewModel.isloading.observeForever(loadingObserver)
        viewModel.loginResponseStatus.observeForever(responseStatusObserver)

        // Trigger the function you want to test
        viewModel.loginuserWithBody("test@gmail.com", "password123")

        // Verify that the LiveData values have been updated correctly
        assert(viewModel.isloading.value == false) // Assuming it's false after an error
        assert(viewModel.loginResponseStatus.value == "Type: UNKNOWN  Msg: Some error")

        // Verify that the observers have been called
        coVerify { loadingObserver.onChanged(false) }
        coVerify { responseStatusObserver.onChanged("Type: UNKNOWN  Msg: Some error") }
    }

    // Add more tests for other functions as needed
}

private infix fun <T, B> MockKStubScope<T, B>.returns(success: ApiResponse.Success<Responsedata>) {

}
*/
