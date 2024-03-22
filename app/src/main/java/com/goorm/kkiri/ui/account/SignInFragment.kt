package com.goorm.kkiri.ui.account

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {
    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {

        // 로그인 -> 회원가입 or 홈 으로 이동
        with(binding) {
            tvBtRegister.setOnClickListener {
                navigateToDestination(SignInFragmentDirections.actionNavigationSignInToNavigationRegister())
            }
            btLogin.setOnClickListener {
                navigateToDestination(SignInFragmentDirections.actionSignInToHome())
            }


            // 해당 editText focus로 이동시키는 부분
            etLoginId.setOnClickListener { v -> v.requestFocus() }
            etLoginPw.setOnClickListener { v -> v.requestFocus() }
        }
    }

    private fun navigateToDestination(action: NavDirections) {
        findNavController().navigate(action)
    }
}
