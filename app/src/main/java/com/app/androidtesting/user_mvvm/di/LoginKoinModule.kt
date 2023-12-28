package com.app.androidtesting.user_mvvm.di

import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterface
import com.app.androidtesting.user_mvvm.data.local.LocalDB
import com.app.androidtesting.user_mvvm.data.remote.ApiService
import com.app.androidtesting.user_mvvm.data.datasource.DataSourceInterfaceImp
import com.app.androidtesting.user_mvvm.data.remote.RetrofitClient
import com.app.androidtesting.user_mvvm.data.repository.LoginRepositoryImp
import com.app.androidtesting.user_mvvm.domain.login.repository.LoginRepository
import com.app.androidtesting.user_mvvm.domain.login.usecase.LoginUseCase
import com.app.androidtesting.user_mvvm.domain.login.usecase.LoginUseCaseImp
import com.app.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object LoginKoinModule {

     val LoginKoinModules = module {
        single<ApiService> { RetrofitClient.getRetrofitApiService() }

        //factory { LocalDB() }
        // single { LocalDB() }
         factory { LocalDB() }

        single<DataSourceInterface> { DataSourceInterfaceImp(get(), get()) }

        single<LoginRepository> { LoginRepositoryImp(get()) }
        single<LoginUseCase> { LoginUseCaseImp(get()) }

        viewModel {
            LoginViewModel(loginUseCase = get())
        }

    }

    //We can also load & inject the KOIN at runtime or dynamic
    private val loadLoginKoinModule by lazy{
        loadKoinModules(LoginKoinModules)
    }

    fun inject() = loadLoginKoinModule
}
