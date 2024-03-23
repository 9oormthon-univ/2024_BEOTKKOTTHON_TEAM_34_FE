package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityImWriteBinding
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class ImWriteActivity : BaseActivity<ActivityImWriteBinding>(R.layout.activity_im_write) {
    private val viewModel: ImWriteViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
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