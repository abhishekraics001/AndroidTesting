package com.bookingcab.androidtesting.login_mvvm.di

import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.login_mvvm.data.datasource.local.LocalDB
import com.bookingcab.androidtesting.login_mvvm.data.datasource.remote.ApiService
import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterfaceImp
import com.bookingcab.androidtesting.login_mvvm.data.datasource.remote.RetrofitClient
import com.bookingcab.androidtesting.login_mvvm.domain.repository.CityItemRepository
import com.bookingcab.androidtesting.login_mvvm.data.repository.CityItemRepositoryImpl
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCase
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCaseImp
import com.bookingcab.androidtesting.login_mvvm.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object LoginKoinModule {
    val appModuleLogin = module {
        single<ApiService> { RetrofitClient.createWebService() }
        factory { LocalDB }
        single<CityItemRepository> { CityItemRepositoryImpl(get()) }
        single<DataSourceInterface> { DataSourceInterfaceImp(get(), get()) }
        single<CityItemsUseCase> { CityItemsUseCaseImp(get()) }
        viewModel {
            LoginViewModel(getItemsUseCase = get())
        }

    }

    //We can also load & inject the KOIN at runtime or dynamic
    private val loadCityKoinModule by lazy{
        loadKoinModules(appModuleLogin)
    }

    fun inject() = loadCityKoinModule
}
