package com.bookingcab.androidtesting.user_mvvm.data.remote

import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.UserSignUpResponseData
import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.CityList
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginResponseData
import com.bookingcab.androidtesting.user_mvvm.data.models.logoutResponse.LogOurResponseData
import com.bookingcab.androidtesting.user_mvvm.data.models.userProfileResponse.UserProfileResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// data/UserService.kt
interface ApiService {

    /**
        HTTP Method:    POST
        HTTP URL:       http://demob2b.bookingcabs.com:3002/
        EndPoint:       api/v1/user/login
        Request Type:   Body ->  x-www-form-urlencoded
        Body Parameters:
                        user_type_id: 3
                        username: test@gmail.com
                        password: 123456
                        callfrom: Android
                        login_location: EC-City 2
                        gcm_id: 123456tdatret254321
                        sms_send_status: true
     */
    @POST("api/v1/user/login")
    suspend fun loginUser(@Body loginRequest: LoginRequestData): Response<LoginResponseData>

    @FormUrlEncoded
    @POST("api/v1/user/login")
    suspend fun loginUser(
        @Field("user_type_id") userTypeId: Int,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("callfrom") callFrom: String,
        @Field("login_location") loginLocation: String,
        @Field("gcm_id") gcmId: String,
        @Field("sms_send_status") smsSendStatus: Boolean
    ): Response<LoginResponseData>


    /**
        http://demob2b.bookingcabs.com:3002/api/v1/user/newsignup
        {"company_id":"1","user_type_id":"3","email":"tes122@gmail.com","password":"123456","mobile":"3322556699","refer_by":"","first_name":"Abhi","last_name":"Rai","nationality":"101","singup_status":"1","mobile_prefix":"91"}

        HTTP Method:        POST
        HTTP URL:           http://demob2b.bookingcabs.com:3002/
        EndPoint:           api/v1/user/newsignup
        Request Type:       Body ->  x-www-form-urlencoded
        Body Parameters:
                            company_id: 1
                            user_type_id: 3
                            email: tes122@gmail.com
                            password: 123456
                            mobile: 3322556699
                            refer_by:
                            first_name: Abhishek
                            last_name: Rai
                            nationality: 101
                            singup_status: 1
                            mobile_prefix: 91
     */
    @POST("api/v1/user/newsignup")
    suspend fun signupUser( @Body userSignupRequestData: UserSignupRequestData) : Response<UserSignUpResponseData>



    /**
        HTTP Method:        GET
        HTTP URL:           http://demob2b.bookingcabs.com:3002/
        End Point:          api/v1/user/userpersonalinfo?
        Request Type:       Query Params
        Query Params
                            user_id: 847
     */
    @GET(" api/v1/user/userpersonalinfo?")
    suspend fun getUserProfileData(
        @Query("user_id") user_id: String
    ): Response<UserProfileResponseData>



    /**
        HTTP Method:        GET
        HTTP URL:           http://demob2b.bookingcabs.com:3002/
        End Point:          api/v1/user/logout?
        Request Type:       Query Params
        Query Params
                            user_id: 847
                            token: 1129f10002ed3729e280a0caaf8cba94db531f0017bc0ab813e885962e1ea584
                            logout_location: karolbag
     */
    @GET("api/v1/user/logout")
    suspend  fun logoutUser(
        @Query("user_id") userId: String,
        @Query("token") token: String,
        @Query("logout_location") logoutLocation: String
    ): Response<LogOurResponseData>




    /**
        HTTP Method:            GET
        HTTP URL:               http://demob2b.bookingcabs.com:3002/
        End Point:              /api/v1/vehicle-type
    */
    @GET("/api/v1/vehicle-type")
    suspend fun getCityList(): Response<CityList>
}