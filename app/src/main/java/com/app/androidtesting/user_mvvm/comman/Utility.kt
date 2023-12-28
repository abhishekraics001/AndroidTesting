package com.app.androidtesting.user_mvvm.comman

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

object Utility {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showAlertDialog(
        context: Context,
        title: String? = "Alert",
        message: String? = "Error Message",
        positiveButtonTitle: String,
        positiveButtonAction: () -> Unit,
        negativeButtonTitle: String? = null,
        negativeButtonAction: (() -> Unit)? = null
    ) {
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonTitle) { _, _ -> positiveButtonAction() }

        negativeButtonTitle?.let {
            builder.setNegativeButton(it) { _, _ -> negativeButtonAction?.invoke() }
        }

        builder.create().show()
    }

}