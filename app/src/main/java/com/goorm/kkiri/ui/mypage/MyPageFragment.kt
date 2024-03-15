package com.goorm.kkiri.ui.mypage

import android.content.Context
import android.content.Intent
import android.widget.TextView
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentMyPageBinding
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun setLayout() {
        //마이페이지 -> 내가 쓴 글 페이지
        onClickIntent(binding.mpWrite,requireActivity())
    }

    //인텐트 함수
    private fun onClickIntent(tv : TextView, context : Context){
        tv.setOnClickListener{
            val intent = Intent(context, ImWriteActivity::class.java)
            startActivity(intent)
        }
    }
}