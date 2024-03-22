package com.goorm.kkiri.ui.search

import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    override fun setLayout() {
        binding.ivSearchBackIcon.setOnClickListener {
            finish()
        }
    }
}