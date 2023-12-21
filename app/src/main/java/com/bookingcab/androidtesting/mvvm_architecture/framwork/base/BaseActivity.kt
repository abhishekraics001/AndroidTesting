package com.bookingcab.androidtesting.mvvm_architecture.framwork.base


import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract  class BaseActivity constructor(@LayoutRes private val layoutRes: Int? = null) : AppCompatActivity() {

    private val tagName = "Activity Method Call"
    private lateinit var tagCurrentActivity : Context ;
    private lateinit var tageCurrentVal : String

    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            if (layoutRes != null) {
                setContentView(layoutRes)
            }
        tagCurrentActivity = this
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onCreate")
    }

   /* override fun setContentView(layoutResID: Int) {
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : setContentView")
        //super.setContentView(layoutResID)
        if (layoutRes != null) {
            super.setContentView(layoutRes)
        }
    }*/

    override fun onStart() {
        super.onStart()
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onDestroy")
    }


    override fun onRestart() {
        super.onRestart()
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : onRestart")
    }


    fun showProgressDialog() {
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : showProgressDialog")
        if(!mProgressDialog.isShowing) {
            mProgressDialog.show()
        }
    }

    fun dismissProgressDialog() {
        Log.e(tagName, "tagCurrentActivity: $tagCurrentActivity  : dismissProgressDialog")
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }





}