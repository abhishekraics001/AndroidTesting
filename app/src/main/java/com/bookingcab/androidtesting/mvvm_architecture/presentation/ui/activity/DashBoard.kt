package com.bookingcab.androidtesting.mvvm_architecture.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.bookingcab.androidtesting.databinding.DashboardPageBinding
import com.bookingcab.androidtesting.user_mvvm.presentation.ui.SignupActivity
import com.bookingcab.androidtesting.user_mvvm.presentation.ui.LoginActivity
import com.bookingcab.mvvm_architecture.framwork.di.LoginAccountDIModules

class DashBoard : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: DashboardPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        LoginAccountDIModules.inject()

      binding = DashboardPageBinding.inflate(layoutInflater)

        setContentView(binding.root)
       setSupportActionBar(binding.toolbar)

       binding.toolbarTitle.text = "DashBoard Page"

       binding.btnLogin.setOnClickListener {
           startActivity(Intent(this, LoginActivity::class.java))
       }

       binding.btnSignup.setOnClickListener {
           startActivity(Intent(this, SignupActivity::class.java))
       }

       binding.btnForgotPassword.setOnClickListener {
           //startActivity(Intent(this, LoginActivity::class.java))
       }

       binding.btnFragmentExample.setOnClickListener {
           startActivity(Intent(this, MainActivity::class.java))
       }

       binding.btnListViewExample.setOnClickListener {
           //startActivity(Intent(this, LoginActivity::class.java))
       }

    }
}