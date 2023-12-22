package com.bookingcab.androidtesting.user_mvvm.di

import com.bookingcab.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.bookingcab.androidtesting.user_mvvm.data.local.LocalDB
import com.bookingcab.androidtesting.user_mvvm.data.remote.ApiService
import com.bookingcab.androidtesting.user_mvvm.data.datasource.DataSourceInterfaceImp
import com.bookingcab.androidtesting.user_mvvm.data.remote.RetrofitClient
import com.bookingcab.androidtesting.user_mvvm.data.repository.UserSignupRepositoryImp
import com.bookingcab.androidtesting.user_mvvm.domain.signup.repository.UserSignupRepository
import com.bookingcab.androidtesting.user_mvvm.domain.signup.usecase.UserSignupUseCase
import com.bookingcab.androidtesting.user_mvvm.domain.signup.usecase.UserSignupUseCaseImp
import com.bookingcab.androidtesting.user_mvvm.presentation.signupVM.UserSignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object SignupKoinModule {

    private val SignupKoinModules = module {
        single<ApiService> { RetrofitClient.getRetrofitApiService() }

        factory { LocalDB }
        single<DataSourceInterface> { DataSourceInterfaceImp(get(), get()) }


        single<UserSignupRepository> { UserSignupRepositoryImp(get()) }
        single<UserSignupUseCase> { UserSignupUseCaseImp(get()) }

        viewModel {
            UserSignupViewModel(userSignupUseCase = get())
        }

    }

    //We can also load & inject the KOIN at runtime or dynamic
    private val loadSignupKoinModule by lazy{
        loadKoinModules(SignupKoinModules)
    }

    fun inject() = loadSignupKoinModule
}
