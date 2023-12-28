package com.app.androidtesting.user_mvvm.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.androidtesting.user_mvvm.comman.LoaderUtils.hideLoader
import com.app.androidtesting.user_mvvm.comman.LoaderUtils.showLoader
import com.app.androidtesting.user_mvvm.comman.Utility
import com.app.androidtesting.user_mvvm.di.LoginKoinModule
import com.app.androidtesting.user_mvvm.presentation.cityVM.CityListViewModel
import com.app.androidtesting.user_mvvm.presentation.loginVM.LoginViewModel
import com.app.androidtesting.user_mvvm.presentation.profileVM.ProfilePage
import com.app.androidtesting.R
import com.app.androidtesting.databinding.LoginPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginPageBinding

    private val vm: CityListViewModel by viewModel()
    private val loginVM: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // CityKoinModule.inject()
        LoginKoinModule.inject()

        binding = LoginPageBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.toolbar.title = "Login Page"

        binding.loginBtn.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
            val emilID = binding.userLoginEmailID.text.toString()
            val password = binding.userLoginPassword.text.toString()
            loginUser(emailsID = emilID, password = password);
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.forgotPassword.setOnClickListener {
            //startActivity(Intent(this, SignupActivity::class.java))
        }



        binding = DataBindingUtil.setContentView(this, R.layout.login_page)
        binding.viewModel = loginVM
        binding.lifecycleOwner = this
    }



    override fun onResume() {
        super.onResume()
       /* vm.cityListData.observe(this) {
            Log.e("liveData ", "liveData for login: $it")
        };

        lifecycleScope.launch {
            vm.getCityList();
        }*/

        loginVM.isloading.observe(this){
            Log.e("loginVM ", "loginVM isloading: $it")
            if(it){
                showLoader(this)
            }else{
                hideLoader()
            }
        }

        loginVM.loginResponseStatus.observe(this){
            Log.e("loginVM ", "loginVM loginResponseStatus: $it")
             Utility.showToast(this, it)
        }

        loginVM.loginResponData.observe(this){
            Log.e("loginVM ", "loginVM loginResponData: $it")
            Utility.showAlertDialog(
                context = this,
                title = "Alert: Success",
                message = "Details Data: " +
                        "Name: ${it.first_name}\n" +
                        "EmaildID: ${it.email}\n" +
                        "Number: ${it.mobile}",
                positiveButtonTitle = "OK, Got to Profile Page",
                positiveButtonAction = {
                    startActivity(Intent(this, ProfilePage::class.java))
                },
                negativeButtonTitle = "Cancel",
                negativeButtonAction = {
                    // Negative button action
                    //Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }



    private fun loginUser(emailsID: String, password: String){
        //loginVM.loginuser(emailsID, password)
        loginVM.loginuserWithBody(emailsID, password)
    }




}