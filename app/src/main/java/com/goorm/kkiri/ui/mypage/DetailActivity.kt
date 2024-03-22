package com.goorm.kkiri.ui.mypage

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityDetailBinding
import com.goorm.kkiri.databinding.ActivityDetailImWriteBinding
import com.goorm.kkiri.ui.home.HomeWritePostActivity
import com.goorm.kkiri.ui.mypage.adapter.IwViewPagerAdapter
import kotlin.math.log

class DetailActivity :
    BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    override fun setLayout() {
        initValue()
        returnResult()
    }

    private fun initValue(){
        with(binding){
            etDtInputPostTitle.setText(intent.getStringExtra("helpTt"))
            etDtInputWritePostNeedBeans.setText(intent.getStringExtra("helpBc").toString())
            etDtInputPostWriteContent.setText(intent.getStringExtra("helpExp"))
        }
    }
    private fun returnResult() {
        with(binding) {
            btnPostWriteCompleteButton.setOnClickListener {
                val returnIntent = Intent()
                returnIntent.putExtra("titleResult", etDtInputPostTitle.text.toString())
                returnIntent.putExtra("BeenCountResult", etDtInputWritePostNeedBeans.text.toString().toLongOrNull() ?: 0L)
                returnIntent.putExtra("ExpResult", etDtInputPostWriteContent.text.toString())
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }

}