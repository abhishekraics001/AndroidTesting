package com.bookingcab.androidtesting.user_mvvm.presentation.cityVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookingcab.androidtesting.user_mvvm.data.models.cityResponse.Data
import com.bookingcab.androidtesting.user_mvvm.domain.cityList.usecase.CityListUseCase
import kotlinx.coroutines.launch

class CityListViewModel(private val cityListUseCase: CityListUseCase) : ViewModel() {

    private val _items = MutableLiveData<List<Data>?>()
    val cityListData: MutableLiveData<List<Data>?> get() = _items


    fun getCityList() {
        viewModelScope.launch {
            val itemDataModels = cityListUseCase.execute()
            //val items = itemDataModels.map { Item(it.id, it.name) }
            val items = itemDataModels.body()?.data
            _items.value = items
        }
    }

}