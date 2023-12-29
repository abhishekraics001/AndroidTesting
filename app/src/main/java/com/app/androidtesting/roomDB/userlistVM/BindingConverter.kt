package com.app.androidtesting.roomDB.userlistVM

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseMethod
import com.bumptech.glide.Glide

        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String?) {
            imageUrl?.let {
                Glide.with(view.context)
                    .load(it)
                    .into(view)
            }
        }

        @BindingAdapter("loadCustomMrTitle")
        fun loadCustomMrTitle(textView: TextView, title: String?) {
            // Implement the logic to load the custom Mr. title
            title?.let {
                textView.text = "Mr. $it"
            }
        }

        fun convertStringToInt(text: String): Int {
            return text.toInt()
        }

        @InverseMethod(value = "convertStringToInt")
        fun convertIntToString(value: Int): String {
            return Integer.toString(value)
        }


        @BindingAdapter("app:intValue")
        fun setIntValue(textView: TextView, value: Int) {
            textView.text = value.toString()
        }

        @InverseBindingAdapter(attribute = "app:intValue")

        fun getIntValue(textView: TextView): Int {
            val text = textView.text.toString()
            return if (text.isEmpty()) 0 else Integer.parseInt(text)
        }


