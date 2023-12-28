package com.app.mvvm_architecture.framwork.di

import com.app.mvvm_architecture.data.datasources.LoginAccountDataSources
import com.app.mvvm_architecture.data.repository.LoginAccountRepositoryImpl
import com.app.mvvm_architecture.domain.repository.LoginAccountRepository
import com.app.mvvm_architecture.domain.usecase.LoginAccountUseCase
import com.app.mvvm_architecture.domain.usecase.LoginAccountUseCaseImpl
import com.app.androidtesting.mvvm_architecture.data.datasources.LoginAccountDataSourcesImpl
import com.app.androidtesting.mvvm_architecture.presentation.viewmodel.LoginAccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


object LoginAccountDIModules {


     val loginAccountModules = module {

        //useCase
        factory<LoginAccountUseCase> { LoginAccountUseCaseImpl(get()) }

        //DataSources
        single<LoginAccountDataSources> { LoginAccountDataSourcesImpl() }

        //Repository
        single<LoginAccountRepository> { LoginAccountRepositoryImpl(get()) }

        //viewModel
        viewModel {
            LoginAccountViewModel(
                loginAccountUseCase = get()
            )
        }
    }



    //We can also load & inject the KOIN at runtime or dynamic
    private val loadKoinModule by lazy{
        loadKoinModules(loginAccountModules)
    }

    fun inject() = loadKoinModule
}