package com.app.androidtesting.user_mvvm.comman

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SimpleViewModel : ViewModel() {
    var myDetails = MutableLiveData<String>()

    fun  getLiveDataValue(name: String, age: Int ){
        viewModelScope.launch {
            myDetails.value = "Hi Mr. $name, Your age is $age and your so....."
        }
    }
}