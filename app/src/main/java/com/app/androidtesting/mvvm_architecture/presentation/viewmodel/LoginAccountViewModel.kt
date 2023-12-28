package com.app.androidtesting.mvvm_architecture.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mvvm_architecture.domain.usecase.LoginAccountUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginAccountViewModel (private val loginAccountUseCase: LoginAccountUseCase ): ViewModel() {


    var _liveData: MutableLiveData<String> = MutableLiveData("ideal");
    var liveData: MutableLiveData<String> = _liveData ; //MutableLiveData("ideal");

    var _logOutStatus : MutableLiveData<Int> = MutableLiveData(1000);
    val logOutStatus : MutableLiveData<Int> = _logOutStatus

            suspend fun loginAccount(){
                delay(100)
                _liveData.value = "init"
                delay(100)

                viewModelScope.launch() {
                    _liveData.value =   loginAccountUseCase.invoke().toString()
                    delay(100)
                    _liveData.value = "complete"
                }

            }

            suspend fun logOutAccount(){
                delay(100)
                _logOutStatus.value = 200
                delay(100)
                _logOutStatus.value = loginAccountUseCase.executeAPICall();
                _logOutStatus.value = 50
            }
}