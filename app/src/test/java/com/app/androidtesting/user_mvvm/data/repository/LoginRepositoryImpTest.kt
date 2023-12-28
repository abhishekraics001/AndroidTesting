package com.app.androidtesting.user_mvvm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.androidtesting.user_mvvm.comman.Constant
import com.app.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.Responsedata
import kotlinx.coroutines.Dispatchers
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class LoginRepositoryImpTest {

    @get: Rule
     var instanceTaskExecutorRule = InstantTaskExecutorRule()

     var standardTestDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var dataSourcesInterface: DataSourceInterface


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
    fun `loginRequest with success`() = runTest{
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)

        Mockito.`when`(dataSourcesInterface.loginRequest(userEmailID, userPassword)).thenReturn(Response.success(lrData))


        //When
        val result  = LoginRepositoryImp(dataSourcesInterface).loginRequest(userEmailID, userPassword)
        standardTestDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result")
        assertEquals(true, result is Response)

        assertTrue( result.isSuccessful)
        assertNull(result.errorBody())
        assertEquals(200,  result.code() )
        assertEquals("OK",  result.message() )

        // Then
        if (result.isSuccessful) {
            val loginResponseData: LoginResponseData? = result.body()
            if (loginResponseData != null) {
                // Access the data from loginResponseData
                val responseData: Responsedata = loginResponseData.responsedata
                val loginData: LoginData = responseData.data

                // Now, you can use loginData as needed
                val userEmail = loginData.email
                val userName = loginData.first_name
                val mobile = loginData.mobile
            } else {
                // Handle the case where the response body is null
            }
        } else {
            // Handle the case where the response is not successful
            val errorBody = result.errorBody()?.string()
            // You can parse and handle the errorBody if needed
        }
    }

    @Test
    fun `loginRequest with Error`() = runTest{
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("$userEmailID", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_FAILED)
        val lrData = LoginResponseData(response)

        Mockito.`when`(dataSourcesInterface.loginRequest(userEmailID, userPassword)).thenReturn(Response.error(402, "Error".toResponseBody("application/json".toMediaTypeOrNull())))


        //When
        val result = LoginRepositoryImp(dataSourcesInterface).loginRequest(userEmailID, userPassword)
        standardTestDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result    || ${ result.message()}")
        assertEquals(true, result is Response)

        assertFalse(result.isSuccessful)
        assertNotNull(result.errorBody())
        assertEquals(402,  result.code() )
        assertEquals("Response.error()",  result.message() )

        // Then
        if (result.isSuccessful) {
            val loginResponseData: LoginResponseData? = result.body()
            if (loginResponseData != null) {
                // Access the data from loginResponseData
                val responseData: Responsedata = loginResponseData.responsedata
                val loginData: LoginData = responseData.data

                // Now, you can use loginData as needed
                val userEmail = loginData.email
                val userName = loginData.first_name
                val mobile = loginData.mobile
            } else {
                // Handle the case where the response body is null
            }
        } else {
            // Handle the case where the response is not successful
            val errorBody = result.errorBody()?.string()
            // You can parse and handle the errorBody if needed
        }
    }


    @Test
    fun `testLoginRequest with success`() = runTest{
        //Mock
        val mockResponseData = LoginData("test@gmail.com", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)

        Mockito.`when`(dataSourcesInterface.loginRequest(loginRequestData)).thenReturn(Response.success(lrData))


        //When
        val result  = LoginRepositoryImp(dataSourcesInterface).loginRequest(loginRequestData)
        standardTestDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result")
        assertEquals(true, result is Response)

        assertTrue( result.isSuccessful)
        assertNull(result.errorBody())
        assertEquals(200,  result.code() )
        assertEquals("OK",  result.message() )

        // Then
        if (result.isSuccessful) {
            val loginResponseData: LoginResponseData? = result.body()
            if (loginResponseData != null) {
                // Access the data from loginResponseData
                val responseData: Responsedata = loginResponseData.responsedata
                val loginData: LoginData = responseData.data

                // Now, you can use loginData as needed
                val userEmail = loginData.email
                val userName = loginData.first_name
                val mobile = loginData.mobile
            } else {
                // Handle the case where the response body is null
            }
        } else {
            // Handle the case where the response is not successful
            val errorBody = result.errorBody()?.string()
            // You can parse and handle the errorBody if needed
        }
    }



    @Test
    fun `testLoginRequest with Error`() = runTest{
        //Mock
        val mockResponseData = LoginData("test@gmail.com", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)

        Mockito.`when`(dataSourcesInterface.loginRequest(loginRequestData)).thenReturn(Response.error(402, "Error".toResponseBody("application/json".toMediaTypeOrNull())))


        //When
        val result  = LoginRepositoryImp(dataSourcesInterface).loginRequest(loginRequestData)
        standardTestDispatcher.scheduler.advanceUntilIdle()


        //Then
        println("result: $result")
        assertEquals(true, result is Response)

        assertFalse(result.isSuccessful)
        assertNotNull(result.errorBody())
        assertEquals(402,  result.code() )
        assertEquals("Response.error()",  result.message() )


        // Then
        if (result.isSuccessful) {
            val loginResponseData: LoginResponseData? = result.body()
            if (loginResponseData != null) {
                // Access the data from loginResponseData
                val responseData: Responsedata = loginResponseData.responsedata
                val loginData: LoginData = responseData.data

                // Now, you can use loginData as needed
                val userEmail = loginData.email
                val userName = loginData.first_name
                val mobile = loginData.mobile
            } else {
                // Handle the case where the response body is null
            }
        } else {
            // Handle the case where the response is not successful
            val errorBody = result.errorBody()?.string()
            // You can parse and handle the errorBody if needed
        }
    }
}