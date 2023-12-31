package com.app.androidtesting.user_mvvm.di

import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.local.LocalDB
import com.app.androidtesting.user_mvvm.data.remote.ApiService
import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterfaceImp
import com.app.androidtesting.user_mvvm.data.remote.RetrofitClient
import com.app.androidtesting.user_mvvm.domain.cityList.repository.CityIListRepository
import com.app.androidtesting.user_mvvm.data.repository.CityListRepositoryImpl
import com.app.androidtesting.user_mvvm.domain.cityList.usecase.CityListUseCase
import com.app.androidtesting.user_mvvm.domain.cityList.usecase.CityIListUseCaseImp
import com.app.androidtesting.user_mvvm.presentation.cityVM.CityListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object CityKoinModule {
    private val CityKoinModules = module {
        single<ApiService> { RetrofitClient.getRetrofitApiService() }

        factory { LocalDB() }
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
