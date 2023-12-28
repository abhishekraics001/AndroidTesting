package com.app.androidtesting.user_mvvm.comman

import android.app.ProgressDialog
import android.content.Context

object LoaderUtils {

    private var progressDialog: ProgressDialog? = null

    fun showLoader(context: Context, message: String = "Loading...") {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog?.setMessage(message)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        }
    }

    fun hideLoader() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            progressDialog = null
        }
    }
}
