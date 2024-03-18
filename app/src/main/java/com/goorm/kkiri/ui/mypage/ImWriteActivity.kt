package com.goorm.kkiri.ui.mypage

import android.content.Intent
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityImWriteBinding


class ImWriteActivity : BaseActivity<ActivityImWriteBinding>(R.layout.activity_im_write) {

    override fun setLayout() {
        setFragment()
        setOnClick()
    }

    private fun setOnClick(){
        with(binding){
            iwDtBt.setOnClickListener {
                startActivity(Intent(this@ImWriteActivity, DetailImWriteActivity::class.java))
            }
        }
    }
    private fun setFragment(){
        with(binding) {
            supportFragmentManager.beginTransaction().apply {
                add(writeFragment.id, ImWriteFragment())
                commit()
            }
        }
    }

}