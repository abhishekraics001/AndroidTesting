package com.bookingcab.androidtesting.user_mvvm.presention

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bookingcab.androidtesting.user_mvvm.comman.Constant
import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.Responsedata
import com.bookingcab.androidtesting.user_mvvm.domain.login.usecase.LoginUseCase
import com.bookingcab.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    //private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var isloadingObserver: Observer<Boolean>

    @Mock
    private lateinit var loginResponDataObserver: Observer<LoginData>

    @Mock
    private lateinit var loginResponseStatusObserver: Observer<String>

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        //MockitoAnnotations.initMocks(this)
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = LoginViewModel(loginUseCase)

        viewModel.isloading.observeForever(isloadingObserver)
        viewModel.loginResponData.observeForever(loginResponDataObserver)
        viewModel.loginResponseStatus.observeForever(loginResponseStatusObserver)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `loginuserWithBody success`() = runTest {
        viewModel.isloading.observeForever(isloadingObserver)


        // Given
        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "123456"

        // Mock the execute function in loginUseCase
        val mockResponseData = LoginData("test@gmail.com", "Abhishek", "878125")
        val x = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val re = LoginResponseData(x);

        Mockito.`when`(loginUseCase.execute(any())).thenReturn(ApiResponse.Success(re))

        // When
        viewModel.loginuserWithBody(viewModel.userEmailID.value!!, viewModel.userPassword.value!!)

        // Then
        verify(isloadingObserver).onChanged(true)
        verify(isloadingObserver).onChanged(false)
        verify(loginResponDataObserver).onChanged(mockResponseData)
        verify(loginResponseStatusObserver).onChanged(Constant.STATUS_SUCCESS)
    }






/*

    @Test
    fun `loginuserWithBody error`() = runTest {
        viewModel.isloading.observeForever(isloadingObserver)

        // Given
        viewModel.userEmailID.value = "test@gmail.com"
        viewModel.userPassword.value = "12345657"



        // Mock the execute function in loginUseCase
        val mockError = ArithmeticException("Mock error message")

       val re =  ApiResponse.Error(Constant.STATUS_FAILED, Exception("Unsuccessful response"))
        Mockito.`when`(loginUseCase.execute(any())).thenReturn(re)


        // When
        viewModel.loginuserWithBody(viewModel.userEmailID.value!!, viewModel.userPassword.value!!)

        // Then
        verify(isloadingObserver).onChanged(true)
        verify(isloadingObserver).onChanged(false)
       // verify(loginResponseStatusObserver).onChanged("Type: ${ApiResponse.Error::class.java.simpleName}  Msg: ${mockError.toString()}")
        //verify(loginResponDataObserver).onChanged(re.errorType.toString())
        verify(loginResponseStatusObserver).onChanged("Failed")
    }
*/



/*


    @Test
    fun `loginuserWithBody error two` () = runTest {
        // Given
        val mockError = ArithmeticException("Mock error message")
        val re =  ApiResponse.Error(Constant.STATUS_FAILED, Exception("Unsuccessful response"))
        Mockito.`when`(loginUseCase.execute(any())).thenReturn(re)

        // When
        viewModel.loginuserWithBody("test@gmail.com", "password123")

        // Then
        // Verify that loading state is set to false after an error
        verify(viewModel.isloading).value?.let {
            assert(it)
        }

        // Verify that loginResponseStatus is updated appropriately
        verify(viewModel.loginResponseStatus).value?.let {
            assert(it.contains("Type:"))
            assert(it.contains("Msg:"))
        }

        // You can add more verifications based on your specific requirements
    }

*/




    @Test
    fun `loginuserWithBody error - ApiResponse Error`() = runTest {
        // Given
        val errorType = Constant.STATUS_FAILED
        val errorMsg = "SomeErrorMessage"
        val exception = Exception("SomeExceptionMessage")

        `when`(loginUseCase.execute(any())).thenReturn(ApiResponse.Error(errorType, exception))
delay(2000)

        // When
        viewModel.loginuserWithBody("test@gmail.com", "password123")

        delay(2000)
        verify(isloadingObserver).onChanged(true)
        verify(isloadingObserver).onChanged(false)

        // Then
        // Verify that loading state is set to false after an error
        assertEquals(false, viewModel.isloading.value)

        delay(2000)
        // Verify that loginResponseStatus is updated appropriately
        assertEquals(errorType,  viewModel.loginResponseStatus.value)

        // You can add more verifications based on your specific requirements
    }

    // Add more test cases for other scenarios

    @Test
    fun `validateEmail success`() {
        // Given
        val validEmail = "test@gmail.com"

        // When
        val result = viewModel.validateEmail(validEmail)

        // Then
        assertEquals(true, result)
        assertEquals(null, viewModel.emailError.value)
    }

    @Test
    fun `validateEmail error`() {
        // Given
        val invalidEmail = "invalidemail"

        // When
        val result = viewModel.validateEmail(invalidEmail)

        // Then
        assertEquals(false, result)
        assertEquals(Constant.INVALID_EMAIL_AMPERSAND_NOT_ERROR, viewModel.emailError.value)
    }

    // Add more validation test cases

    // Add more tests for other methods as needed
}
