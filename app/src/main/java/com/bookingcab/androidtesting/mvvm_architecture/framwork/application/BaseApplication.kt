package com.bookingcab.clientapp.framwork.application

import android.app.Application
import com.bookingcab.androidtesting.user_mvvm.di.LoginKoinModule
import com.bookingcab.mvvm_architecture.framwork.di.LoginAccountDIModules.loginAccountModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


open class BaseApplication  : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
            androidContext(this@BaseApplication)
            modules(loginAccountModules)
        }
    }
}