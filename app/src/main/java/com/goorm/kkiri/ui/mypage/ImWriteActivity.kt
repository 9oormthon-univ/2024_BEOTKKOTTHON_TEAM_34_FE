package com.goorm.kkiri.ui.mypage

import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityImWriteBinding


class ImWriteActivity : BaseActivity<ActivityImWriteBinding>(R.layout.activity_im_write) {

    override fun setLayout() {
        supportFragmentManager.beginTransaction().apply {
            add(binding.writeFragment.id, ImWriteFragment())
            commit()
        }


    }


}