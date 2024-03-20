package com.goorm.kkiri.ui.home.viewmodel

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.goorm.kkiri.KkiriApplication
import com.goorm.kkiri.R
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeViewModel : ViewModel() {

    fun changeHelpMeBackgroundTint(isHelpMe: Boolean): Int {
        return if (isHelpMe) {
            ContextCompat.getColor(KkiriApplication.application, R.color.blue_main)
        } else ContextCompat.getColor(KkiriApplication.application, R.color.gray_20)
    }

    fun changeHelpYouBackgroundTint(isHelpYou: Boolean): Int {
        return if (isHelpYou) {
            ContextCompat.getColor(KkiriApplication.application, R.color.blue_main)
        } else ContextCompat.getColor(KkiriApplication.application, R.color.gray_20)
    }
}