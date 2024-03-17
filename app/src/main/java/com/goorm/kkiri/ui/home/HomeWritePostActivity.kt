package com.goorm.kkiri.ui.home

import androidx.navigation.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityHomeWritePostBinding

class HomeWritePostActivity : BaseActivity<ActivityHomeWritePostBinding>(R.layout.activity_home_write_post) {
    private val args by navArgs<HomeWritePostActivityArgs>()

    override fun setLayout() {
        setClickBackButton()
        setWriteCompleteButton()
    }

    private fun setClickBackButton() {
        binding.toolbarHomeWritePost.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setWriteCompleteButton() {
        binding.btnPostWriteCompleteButton.setOnClickListener {
            finish()
        }
    }
}