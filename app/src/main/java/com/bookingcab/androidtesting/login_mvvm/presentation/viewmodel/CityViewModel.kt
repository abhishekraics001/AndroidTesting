package com.bookingcab.androidtesting.login_mvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookingcab.androidtesting.login_mvvm.data.models.Data
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCase
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCaseImp
import kotlinx.coroutines.launch

class CityViewModel(private val getItemsUseCase: CityItemsUseCase) : ViewModel() {

    private val _items = MutableLiveData<List<Data>?>()
    val items: MutableLiveData<List<Data>?> get() = _items


    fun loadItems(userEmailID: String, userPassword: String) {
        viewModelScope.launch {
            val itemDataModels = getItemsUseCase.execute(userEmailID, userPassword)
            //val items = itemDataModels.map { Item(it.id, it.name) }
            val items = itemDataModels.body()?.data
            _items.value = items
        }
    }


}