package com.app.androidtesting.user_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.androidtesting.user_mvvm.di.SignupKoinModule
import com.app.androidtesting.user_mvvm.presentation.signupVM.UserSignupViewModel
import com.app.androidtesting.databinding.SignupPageBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: SignupPageBinding

    private val signupVM: UserSignupViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SignupKoinModule.inject()

        binding = SignupPageBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.toolbar.title = "Signup Page"




        binding.btnSignup.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
            val email = binding.signupEmailID.text.toString()
            val pass = binding.signupPassword.text.toString()
            val mobileNo = binding.signupMobileNo.text.toString()
            val name = binding.signupUserName.text.toString()

            userSignup(email, pass, mobileNo, name);
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupForgotPassword.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }

    }


    override fun onResume() {
        super.onResume()

        signupVM.isloading.observe(this){
            Log.e("loginVM ", "loginVM isloading: $it")
        }
        signupVM.signupResponseStatus.observe(this){
            Log.e("loginVM ", "loginVM loginResponseStatus: $it")
        }

        signupVM.signupResponData.observe(this){
            Log.e("loginVM ", "loginVM loginResponData: $it")
        }
    }

    fun userSignup(emilID: String, password: String, mobileNo: String,  firstName: String){
        lifecycleScope.launch {
            signupVM.userSignup(emilID, password, mobileNo, firstName)
        }
    }
}