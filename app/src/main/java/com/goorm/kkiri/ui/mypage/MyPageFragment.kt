package com.goorm.kkiri.ui.mypage

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentMyPageBinding
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    override fun setLayout() {
        //마이페이지 -> 내가 쓴 글 페이지
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            mpWrite.setOnClickListener {
                val action = MyPageFragmentDirections.actionMyPageToImWrite()
                findNavController().navigate(action)
            }
        }
    }
}