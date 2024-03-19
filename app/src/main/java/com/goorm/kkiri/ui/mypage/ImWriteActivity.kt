package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityImWriteBinding
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel

@RequiresApi(Build.VERSION_CODES.O)
class ImWriteActivity : BaseActivity<ActivityImWriteBinding>(R.layout.activity_im_write) {
    private lateinit var viewModel: ImWriteViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
        viewModel = ViewModelProvider(this).get(ImWriteViewModel::class.java)
        setFragment()
        setOnClick()

    }

    private fun setOnClick() {
        with(binding) {
            iwDtBt.setOnClickListener {
                viewModel.removeFirstItem()
            }

            iwDtBt2.setOnClickListener {
                viewModel.createFirstItem()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setFragment(){
        with(binding) {
            supportFragmentManager.beginTransaction().apply {
                add(writeFragment.id, ImWriteFragment())
                commit()
            }
        }
    }

}