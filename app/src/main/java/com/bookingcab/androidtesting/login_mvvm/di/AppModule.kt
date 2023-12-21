package com.bookingcab.androidtesting.login_mvvm.di

import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.login_mvvm.data.datasource.local.LocalDB
import com.bookingcab.androidtesting.login_mvvm.data.datasource.remote.ApiService
import com.bookingcab.androidtesting.login_mvvm.data.datasource.DataSourceInterfaceImp
import com.bookingcab.androidtesting.login_mvvm.domain.repository.CityItemRepository
import com.bookingcab.androidtesting.login_mvvm.data.repository.CityItemRepositoryImpl
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCase
import com.bookingcab.androidtesting.login_mvvm.domain.usecase.CityItemsUseCaseImp
import com.bookingcab.androidtesting.login_mvvm.presentation.viewmodel.CityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    val appModuleLogin = module {

        single<ApiService> { createWebService() }
        factory { LocalDB }
        single<CityItemRepository> { CityItemRepositoryImpl(get()) }
        single<DataSourceInterface> { DataSourceInterfaceImp(get(), get()) }
        single<CityItemsUseCase> { CityItemsUseCaseImp(get()) }

        viewModel {
            CityViewModel(getItemsUseCase = get())
        }


    }

    //We can also load & inject the KOIN at runtime or dynamic
    private val loadCityKoinModule by lazy{
        loadKoinModules(AppModule.appModuleLogin)
    }

    fun inject() = loadCityKoinModule
}


//https://api.bookingcabs.com:3001/api/v1/vehicle-type
private inline fun <reified T> createWebService(): T {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://demob2b.bookingcabs.com:3002")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}
