package com.bookingcab.androidtesting.user_mvvm.di

import com.bookingcab.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.user_mvvm.data.local.LocalDB
import com.bookingcab.androidtesting.user_mvvm.data.remote.ApiService
import com.bookingcab.androidtesting.user_mvvm.data.datasource.DataSourceInterfaceImp
import com.bookingcab.androidtesting.user_mvvm.data.remote.RetrofitClient
import com.bookingcab.androidtesting.user_mvvm.domain.cityList.repository.CityIListRepository
import com.bookingcab.androidtesting.user_mvvm.data.repository.CityListRepositoryImpl
import com.bookingcab.androidtesting.user_mvvm.data.repository.LoginRepositoryImp
import com.bookingcab.androidtesting.user_mvvm.domain.cityList.usecase.CityListUseCase
import com.bookingcab.androidtesting.user_mvvm.domain.cityList.usecase.CityIListUseCaseImp
import com.bookingcab.androidtesting.user_mvvm.domain.login.repository.LoginRepository
import com.bookingcab.androidtesting.user_mvvm.domain.login.usecase.LoginUseCase
import com.bookingcab.androidtesting.user_mvvm.domain.login.usecase.LoginUseCaseImp
import com.bookingcab.androidtesting.user_mvvm.presentation.cityVM.CityListViewModel
import com.bookingcab.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object CityKoinModule {
    private val CityKoinModules = module {
        single<ApiService> { RetrofitClient.getRetrofitApiService() }

        factory { LocalDB }
        single<DataSourceInterface> { DataSourceInterfaceImp(get(), get()) }


        single<CityIListRepository> { CityListRepositoryImpl(get()) }
        single<CityListUseCase> { CityIListUseCaseImp(get()) }

        viewModel {
            CityListViewModel(cityListUseCase = get())
        }


    }

    //We can also load & inject the KOIN at runtime or dynamic
    private val loadCityKoinModule by lazy{
        loadKoinModules(CityKoinModules)
    }

    fun inject() = loadCityKoinModule
}
