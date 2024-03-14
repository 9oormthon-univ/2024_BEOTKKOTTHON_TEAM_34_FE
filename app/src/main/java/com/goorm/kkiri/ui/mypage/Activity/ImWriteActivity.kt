package com.goorm.kkiri.ui.mypage.Activity

import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityImWriteBinding
import com.goorm.kkiri.ui.mypage.fragment.ImWriteFragment

class ImWriteActivity : BaseActivity<ActivityImWriteBinding>(R.layout.activity_im_write) {

    override fun setLayout() {
        supportFragmentManager.beginTransaction().apply {
            add(binding.writeFragment.id, ImWriteFragment())
            commit()
        }


    }


}