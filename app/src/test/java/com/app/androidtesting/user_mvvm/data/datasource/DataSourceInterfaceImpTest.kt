package com.app.androidtesting.user_mvvm.data.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.androidtesting.user_mvvm.comman.Constant
import com.app.androidtesting.user_mvvm.data.local.LocalDB
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.app.androidtesting.user_mvvm.data.models.loginResponse.Responsedata
import com.app.androidtesting.user_mvvm.data.remote.ApiService
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
import org.mockito.kotlin.any
import retrofit2.Response


class DataSourceInterfaceImpTest {

    @get: Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    var standardTestDispatcher = StandardTestDispatcher()

    @Mock
     var localDB  =  LocalDB()

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

   /* @Test
    fun getCityDataFromLocal() {
    }

    @Test
    fun getDataFromRemoteAPi() {
    }*/

    @Test
    fun `loginRequest with success ` () = runTest {
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("test@gmail.com", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)

        //WHEN
        Mockito.`when`(apiService.loginUser(3, "test@gmail.com", "123456","Android","EC-2","4231fhgsa", true)).thenReturn(
            Response.success(lrData))

        //THEN
        val result = DataSourceInterfaceImp(apiService, localDB).loginRequest("test@gmail.com", "123456")
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
    fun `loginRequest with failed ` () = runTest {
        //Given
        val userEmailID = "test@gmail.com";
        val userPassword = "123456"


        //Mock
        val mockResponseData = LoginData("test@gmail.com", "Abhishek", "878125")
        val response = Responsedata(mockResponseData, Constant.STATUS_SUCCESS)
        val lrData = LoginResponseData(response)

        //WHEN
        Mockito.`when`(apiService.loginUser(3, "test@gmail.com", "123456","Android","EC-2","4231fhgsa", true)).thenReturn(
            Response.error(402, "Error".toResponseBody("application/json".toMediaTypeOrNull())))

        //THEN
        val result = DataSourceInterfaceImp(apiService, localDB).loginRequest("test@gmail.com", "123456")
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


  /*  @Test
    fun testLoginRequest() {
    }

    @Test
    fun signupRequest() {
    }*/
}