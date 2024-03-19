package com.goorm.kkiri.ui.extensions

import android.widget.TextView
import androidx.core.content.ContextCompat.getString
import androidx.databinding.BindingAdapter
import com.goorm.kkiri.R

@BindingAdapter("setBeans")
fun TextView.setBeans(count: Int) {
    val text = getString(context, R.string.text_beans_count).format(count)
    setText(text)
}