package com.app.androidtesting.user_mvvm.domain.login.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.androidtesting.user_mvvm.comman.Constant
import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.Responsedata
import com.app.androidtesting.user_mvvm.domain.login.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseImpTest {

    @get: Rule
    val instanceTaskExecutor = InstantTaskExecutorRule()

    val testDispatcher = StandardTestDispatcher()


    @Mock
    private lateinit var loginRepo : LoginRepository


    val loginRequestData = LoginRequestData(
        user_type_id = 3,
        username = "test@gmail.com",
        password = "test@gmail.com",
        //username = "test@gmail.com",
        //password = "123456",
        callfrom = "Android",
        login_location = "EC2",
        gcm_id = "56342hjgsda",
        sms_send_status = true
    );


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.IO)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @Test
     fun `LoginUseCase with query param execute success case` () = runTest{
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)
        Mockito.`when`(loginRepo.loginRequest(userEmailID, userPassword)).thenReturn(Response.success(lrData))


        //When
        val result : ApiResponse<LoginResponseData> = LoginUseCaseImp(loginRepo).execute(userEmailID, userPassword)
        testDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result")
        assertEquals(true, result is ApiResponse.Success)
    }


    @Test
    fun `LoginUseCase with query param execute in success case 2`() = runTest {
        // Given
        val userEmailID = "test@gmail.com"
        val userPassword = "123456"

        // Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)
        Mockito.`when`(loginRepo.loginRequest(userEmailID, userPassword)).thenReturn(Response.success(lrData))

        // When
        val result: ApiResponse<LoginResponseData> = LoginUseCaseImp(loginRepo).execute(userEmailID, userPassword)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        println("result: $result")

        // Additional assertions or verifications based on your test requirements
        assertEquals(true, result is ApiResponse.Success)

        // Extracting data from the result
        when (result) {
            is ApiResponse.Success -> {
                val loginResponseData = result.data.responsedata
                val loginData = loginResponseData.data
                println("result loginData: $loginData")

                // Print or use the extracted data
                println("User Email: ${loginData.email}")
                println("User Name: ${loginData.first_name}")
                println("User mobile: ${loginData.mobile}")

                // Assertions based on the extracted data
                assertEquals(userEmailID, loginData.email)
                assertEquals("Abhishek", loginData.first_name)
                assertEquals("878125", loginData.mobile)
            }
            is ApiResponse.Error -> {
                // Handle error case if needed
                fail("Unexpected error in the ApiResponse: $result")
            }
        }
    }


    @Test
    fun `LoginUseCase with query param execute in failed case`() = runTest {
        // Given
        val userEmailID = "test2@gmail.com"
        val userPassword = "1234566"

        // Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_FAILED)
        val lrData = LoginResponseData(response)
       // Mockito.`when`(loginRepo.loginRequest(userEmailID, userPassword)).thenReturn(Response.error(402, lrData))
        Mockito.`when`(loginRepo.loginRequest(userEmailID, userPassword)).thenReturn(Response.error(402, "".toResponseBody("application/json".toMediaTypeOrNull())))


        // When
        val result: ApiResponse<LoginResponseData> = LoginUseCaseImp(loginRepo).execute(userEmailID, userPassword)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        println("result: $result")

        // Additional assertions or verifications based on your test requirements
        assertEquals(true, result is ApiResponse.Error)

        // Extracting data from the result
        when (result) {
            is ApiResponse.Success -> {
                val loginResponseData = result.data.responsedata
                val loginData = loginResponseData.data
                println("result loginData: $loginData")

                // Assertions based on the extracted data
                assertEquals(userEmailID, loginData.email)
                assertEquals("Abhishek", loginData.first_name)
                assertEquals("878125", loginData.mobile)
            }
            is ApiResponse.Error -> {
                // Handle error case if needed
                //fail("Unexpected error in the ApiResponse: $result. ")
                assertEquals(Constant.STATUS_FAILED, result.errorType)
            }
        }
    }






    @Test
    fun `LoginUseCase with body request data execute with success` () = runTest{
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)
        Mockito.`when`(loginRepo.loginRequest(loginRequestData)).thenReturn(Response.success(lrData))


        //When
        val result = LoginUseCaseImp(loginRepo).execute(loginRequestData)
        testDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result")
        assertEquals(true, result is ApiResponse.Success)
    }


    @Test
    fun `LoginUseCase with body request data execute with failed` () = runTest{
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"

        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)
        Mockito.`when`(loginRepo.loginRequest(loginRequestData)).thenReturn(Response.error(402, "".toResponseBody("application/json".toMediaTypeOrNull())))

        //When
        val result = LoginUseCaseImp(loginRepo).execute(loginRequestData)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        println("result: $result")
        assertEquals(true, result is ApiResponse.Error)

        // Extracting data from the result
        when (result) {
            is ApiResponse.Success -> {
                val loginResponseData = result.data.responsedata
                val loginData = loginResponseData.data
            }
            is ApiResponse.Error -> {
                // Handle error case if needed
                //fail("Unexpected error in the ApiResponse: $result. ")
                assertEquals(Constant.STATUS_FAILED, result.errorType)
            }
        }
    }




    //@Test
    fun `LoginUseCase with body request data execute with failed with exception` () = runTest{
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"

        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)
        //Mockito.`when`(loginRepo.loginRequest(loginRequestData)).thenReturn(Response.error(402, Exception("IOException")))
        //Mockito.`when`(loginRepo.loginRequest(loginRequestData)).thenThrow(Exception("Simulated error"))
        //whenever(loginRepo.loginRequest(loginRequestData)).thenThrow(Exception("Simulated error"))


        //When
        val result = LoginUseCaseImp(loginRepo).execute(loginRequestData)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        println("result: $result")
        //assertEquals(true, result is ApiResponse.Error)
       // assertTrue(result is ApiResponse.Error)
        val error = (result as ApiResponse.Error).exception
        assertEquals("Your expected error message", error.message)


    }



    @Test
    fun executeWithBody() {
    }
}