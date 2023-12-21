package com.bookingcab.androidtesting.login_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ui.AppBarConfiguration
import com.bookingcab.androidtesting.databinding.LoginPageBinding
import com.bookingcab.androidtesting.login_mvvm.di.LoginKoinModule
import com.bookingcab.androidtesting.login_mvvm.presentation.viewmodel.LoginViewModel
import com.bookingcab.androidtesting.mvvm_architecture.activity.SignupActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: LoginPageBinding

    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
       // WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        LoginKoinModule.inject()

        binding = LoginPageBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.toolbar.title = "Login Page"

        binding.loginBtn.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }


        //getCityList("", "");
/*
        vm.items.observe(this){
            print("view Model Data: $it")
        }
        vm.loadItems("", "");*/

    }

    override fun onResume() {
        super.onResume()

        vm.items.observe(this) {
            Log.e("liveData ", "liveData for login: $it")
        };
        lifecycleScope.launch {
            vm.loadItems("abh", "rai");
        }
    }


/*    private fun getCityList(userEmail: String, userPassword: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                    val result = RetrofitClient.apiService.getCityList()
                    if (result != null)
                    // Checking the results
                        Log.e("Received city list ayush: ", result.body().toString())
            } catch (e: Exception) {
                // Handle the exception
                println("Received city list Error: ${e.message}")
            }
        }
    }*/




}