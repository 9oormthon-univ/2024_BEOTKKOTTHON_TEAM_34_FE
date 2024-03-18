package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityDetailImWriteBinding
import com.goorm.kkiri.ui.home.HomeWritePostActivity
import com.goorm.kkiri.ui.mypage.adapter.IwViewPagerAdapter

class DetailImWriteActivity :
    BaseActivity<ActivityDetailImWriteBinding>(R.layout.activity_detail_im_write),
    DetailImWriteDialogInterface {
    override fun setLayout() {
        settingListClickEvent()
        onImageAttachedViewPager()
    }


    // add 버튼, back 버튼 클릭 이벤트
    private fun settingListClickEvent() {
        with(binding) {
            iwAddMenu.setOnClickListener {
                addMenu()
            }
            iwWtBack.setOnClickListener {
                //수정 내용 가지고 뒤로가기
                finish()
            }
        }
    }


    //add 버튼 상세 구현, 다이얼로그 띄우기
    private fun addMenu() {
        val dialog = DetailImWritDialog(
            this,
            "게시글 수정",
            "게시글 삭제",
            0
        )
        dialog.isCancelable = true
        dialog.showNow(supportFragmentManager, "DetailImWritDialog")
    }


    // 1번 edit, 2번 remove  다이얼로그 내용 각각 클릭 이벤트
    override fun onClickButton(id: Int) {
        when (id) {
            1 -> startActivity(
                Intent(
                    this@DetailImWriteActivity,
                    HomeWritePostActivity::class.java
                )
            ) // 게시글 수정
            2 -> finish() // 게시글 삭제
        }

    }


    //이미지와 뷰페이저 연결
    private fun onImageAttachedViewPager() {

        with(binding) {
            // IwViewPagerAdapter 인스턴스 생성
            val iwViewPagerAdapter = IwViewPagerAdapter(this@DetailImWriteActivity)

            // ViewPager2에 어댑터 설정
            iwViewPager.adapter = iwViewPagerAdapter

            iwViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    // 페이지가 스크롤될 때 호출
                }

                override fun onPageSelected(position: Int) {
                    updateIndicators(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    // 페이지 스크롤 상태가 변경될 때 호출
                }
            })

        }
    }

    //인디케이터에 이미지 세팅
    private fun setIndicator(imgView: ImageView, drawable: Drawable?) {
        imgView.setImageDrawable(drawable)
    }


    //인디케이터 업데이트
    private fun updateIndicators(selectedPosition: Int) {
        val defaultDrawable = AppCompatResources.getDrawable(
            this@DetailImWriteActivity,
            R.drawable.shape_indicator_gray_12dp
        )
        val selectedDrawable = AppCompatResources.getDrawable(
            this@DetailImWriteActivity,
            R.drawable.shape_indicator_white_12dp
        )

        val indicators = listOf(
            binding.indicator0IvMain,
            binding.indicator1IvMain,
            binding.indicator2IvMain,
        )

        indicators.forEachIndexed { index, imageView ->
            setIndicator(
                imageView,
                if (index == selectedPosition) selectedDrawable else defaultDrawable
            )
        }
    }

}