package com.goorm.kkiri.ui.detail

import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityDetailPostInfoBinding

class DetailPostInfoActivity : BaseActivity<ActivityDetailPostInfoBinding>(R.layout.activity_detail_post_info) {
    override fun setLayout() {
        setBackButton()
    }

    private fun setBackButton() {
        binding.toolbarDetailPost.setNavigationOnClickListener {
            finish()
        }
    }
}