package com.goorm.kkiri.ui.mypage

import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivityDetailImWriteBinding

class DetailImWriteActivity :
    BaseActivity<ActivityDetailImWriteBinding>(R.layout.activity_detail_im_write),
    DetailImWriteDialogInterface {
    override fun setLayout() {
        settingListClickEvent()
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
            1 -> "" // 게시글 수정
            2 -> finish() // 게시글 삭제
        }

    }


}