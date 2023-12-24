package com.bookingcab.androidtesting.user_mvvm.presentation.loginVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookingcab.androidtesting.user_mvvm.comman.Constant
import com.bookingcab.androidtesting.user_mvvm.comman.EmailValidator
import com.bookingcab.androidtesting.user_mvvm.comman.PasswordValidator
import com.bookingcab.androidtesting.user_mvvm.data.datasource.ApiResponse
import com.bookingcab.androidtesting.user_mvvm.data.models.loginRequest.LoginRequestData
import com.bookingcab.androidtesting.user_mvvm.data.models.loginResponse.LoginData
import com.bookingcab.androidtesting.user_mvvm.domain.login.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {


    val userEmailID  =  MutableLiveData<String>()

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError


    val userPassword  = MutableLiveData<String>()
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError



    private var _isloading = MutableLiveData<Boolean>()
    val isloading : MutableLiveData<Boolean> get() = _isloading

    private var _loginResponseStatus = MutableLiveData<String>()
    val loginResponseStatus : MutableLiveData<String> get() = _loginResponseStatus

    private var _loginResponData = MutableLiveData<LoginData>()
    val loginResponData : MutableLiveData<LoginData> get() =  _loginResponData

    init {
        userEmailID.value = "test@gmail.com"
        userPassword.value = "123456"
    }


  /*  fun loginuser(userEmailID: String, userPassword: String){
            viewModelScope.launch {
                try {
                    _isloading.value = true
                    val result = withContext(Dispatchers.IO) {
                        loginUseCase.execute(userEmailID, userPassword)
                    }
                    when (result) {
                        is ApiResponse.Success -> {
                            _loginResponData.value = result.data.responsedata.data
                            _loginResponseStatus.value = "Success"
                            _isloading.value = false
                        }
                        is ApiResponse.Error -> {
                            _loginResponseStatus.value = "Type: ${result.errorType}  Msg: ${result.exception.toString()}"
                            _isloading.value = false
                        }
                    }
                } catch (e: Exception) {
                    _loginResponseStatus.value = "Type: Error  Msg: ${e.toString()}"
                    _isloading.value = false
                }
            }
    }

*/
    fun loginuserWithBody(userEmailID: String, userPassword: String){
        viewModelScope.launch{
            if(isValidCredentials()) {
                try {
                    _isloading.value = true
                    val result = withContext(Dispatchers.IO) {
                        val loginRequestData = LoginRequestData(
                            user_type_id = 3,
                            username = if (userEmailID.length > 2) userEmailID else "test@gmail.com",
                            password = userPassword.takeIf { it.length > 2 } ?: "test@gmail.com",
                            //username = "test@gmail.com",
                            //password = "123456",
                            callfrom = "Android",
                            login_location = "EC2",
                            gcm_id = "56342hjgsda",
                            sms_send_status = true
                        );

                        loginUseCase.execute(loginRequestData)
                    }
                    when (result) {
                        is ApiResponse.Success -> {
                            _loginResponData.value = result.data.responsedata.data
                            _loginResponseStatus.value = Constant.STATUS_SUCCESS
                            _isloading.value = false
                        }
                        is ApiResponse.Error -> {
                            _loginResponseStatus.value = Constant.STATUS_FAILED //"Type: ${result.errorType}  Msg: ${result.exception.toString()}"
                            _isloading.value = false
                        }
                    }
                } catch (e: Exception) {
                    _loginResponseStatus.value = Constant.STATUS_FAILED //"Type: Error  Msg: ${e.toString()}"
                    _isloading.value = false
                }
            }else{
                _loginResponseStatus.value = Constant.STATUS_FAILED; //"Validation Error: Enter the valid user email id & password"
                _isloading.value = false
            }
        }
    }






    fun validatePassword(password: String): Boolean {
        _passwordError.value = PasswordValidator.getPasswordErrorMessage(password)
        return _passwordError.value == null
    }

    fun validateEmail(email: String): Boolean {
        _emailError.value = EmailValidator.getEmailErrorMessage(email)
        return _emailError.value == null
    }


    fun  isValidCredentials() : Boolean {
        return if(!validateEmail(userEmailID.value.toString())) {
            _loginResponseStatus.value = "Validation Error: Enter the valid user email id & password"
            false
        }else if(!validatePassword(userPassword.value.toString())){
            _loginResponseStatus.value = "Validation Error: Enter the valid password"
            false
        } else{
            true
        }
    }


    fun onLoginButtonClicked() {
        if (isValidCredentials()){
            //loginuser(userEmailID.value.toString(), userPassword.value.toString())
            loginuserWithBody(userEmailID.value.toString(), userPassword.value.toString())
        }
    }





}