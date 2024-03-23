package com.goorm.kkiri.ui.extensions

import android.widget.TextView
import androidx.core.content.ContextCompat.getString
import androidx.databinding.BindingAdapter
import com.goorm.kkiri.R

@BindingAdapter("setBeans")
fun TextView.setBeans(count: Long) {
    val text = getString(context, R.string.text_beans_count).format(count)
    setText(text)
}

@BindingAdapter("setNeedBeans")
fun TextView.setNeedBeans(count: Int) {
    val text = getString(context, R.string.text_my_beans_number).format(count)
    setText(text)
}

@BindingAdapter("setNeedBeans")
fun TextView.setNeedBeans(count: Long) {
    val text = getString(context, R.string.text_my_beans_number).format(count)
    setText(text)
}

@BindingAdapter("setDateFormat")
fun TextView.setDateFormat(input: String?) {
    if (input != null) {
        val date = input.substring(0, 10)
        text = date
    }
}