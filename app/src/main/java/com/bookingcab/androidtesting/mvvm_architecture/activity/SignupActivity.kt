package com.bookingcab.androidtesting.mvvm_architecture.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.bookingcab.androidtesting.databinding.SignupPageBinding
import com.bookingcab.androidtesting.login_mvvm.presentation.ui.LoginActivity
import com.bookingcab.mvvm_architecture.framwork.di.LoginAccountDIModules

class SignupActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: SignupPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        LoginAccountDIModules.inject()

        binding = SignupPageBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.toolbar.title = "Signup Page"




        binding.btnSignup.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupForgotPassword.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }

    }
}