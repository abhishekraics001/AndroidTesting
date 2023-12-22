package com.bookingcab.androidtesting.user_mvvm.presentation.signupVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpRequest.UserSignupRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.SignUpResponse.Data
import com.bookingcab.androidtesting.user_mvvm.domain.signup.usecase.UserSignupUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSignupViewModel(private val userSignupUseCase: UserSignupUseCase) : ViewModel(){

    private var _isloading = MutableLiveData<Boolean>()
    val isloading : MutableLiveData<Boolean> get() = _isloading

    private var _signupResponseStatus = MutableLiveData<String>()
    val signupResponseStatus : MutableLiveData<String> get() = _signupResponseStatus

    private var _signupResponData = MutableLiveData<Data>()
    val signupResponData : MutableLiveData<Data> get() =  _signupResponData



    fun userSignup(emilID: String, password: String, mobileNo: String,  firstName: String){
        viewModelScope.launch {

                try {
                   val result =  withContext(Dispatchers.IO) {
                       val userSignupRequestData = UserSignupRequestData(
                           company_id = "1",
                           user_type_id = "3",
                           email = emilID,
                           password = password,
                           mobile = mobileNo,
                           refer_by = "",
                           first_name = firstName,
                           last_name = "Rai",
                           nationality = "101",
                           singup_status = "1",
                           mobile_prefix = "91"
                       )

                       userSignupUseCase.UserSignupRequestData(userSignupRequestData)
                    }
                    when(result){
                        is ApiResponse.Success -> {
                            _signupResponData.value = result.data.responsedata.data
                            _signupResponseStatus.value = "Success"
                            _isloading.value = false
                        }

                        is ApiResponse.Error ->{
                            _signupResponseStatus.value = "Type: ${result.errorType}  Msg: ${result.exception.toString()}"
                            _isloading.value = false
                        }
                    }
                }catch (e: Exception){
                    _signupResponseStatus.value = "Type: Error  Msg: ${e.toString()}"
                    _isloading.value = false
            }
        }
    }
}