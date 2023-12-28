package com.app.clientapp.framwork.application

import android.app.Application
import com.app.mvvm_architecture.framwork.di.LoginAccountDIModules.loginAccountModules
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