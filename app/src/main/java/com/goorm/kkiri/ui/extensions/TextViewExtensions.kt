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

@BindingAdapter("setUserName")
fun TextView.setUserName(name: String?) {
    if (name != null) {
        val text = getString(context, R.string.text_home_user_name).format(name)
        setText(text)
    }
}

@BindingAdapter("setNeedBeans")
fun TextView.setNeedBeans(count: Int?) {
    if (count != null) {
        val text = getString(context, R.string.text_my_beans_number).format(count)
        setText(text)
    }
}

@BindingAdapter("setNeedBeans")
fun TextView.setNeedBeans(count: Long?) {
    if (count != null) {
        val text = getString(context, R.string.text_my_beans_number).format(count)
        setText(text)
    }
}

@BindingAdapter("setDateFormat")
fun TextView.setDateFormat(input: String?) {
    if (input != null) {
        val date = input.substring(0, 10)
        text = date
    }
}

@BindingAdapter("setNumber")
fun TextView.setNumber(count: Long?) {
    if (count != null) {
        val text = getString(context, R.string.text_number).format(count)
        setText(text)
    }
}

@BindingAdapter("setRealMoneyHelp")
fun TextView.setRealMoney(input: Long?) {
    if (input != null) {
        val text = getString(context, R.string.text_guide_help).format((input * 2500))
        setText(text)
    }
}

@BindingAdapter("setRealMoneyHelped")
fun TextView.setRealMoneyHelped(input: Long?) {
    if (input != null) {
        val text = getString(context, R.string.text_guide_helped).format((input * 2500))
        setText(text)
    }
}