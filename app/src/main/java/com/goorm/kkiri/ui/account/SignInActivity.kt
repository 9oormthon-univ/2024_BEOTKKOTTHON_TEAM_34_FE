package com.goorm.kkiri.ui.account

import android.content.Intent
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.databinding.ActivitySignInBinding
import com.goorm.kkiri.ui.MainActivity

class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {

        // 로그인 -> 회원가입 or 홈 으로 이동
        with(binding) {
            tvBtRegister.setOnClickListener {
                val intent = Intent(this@SignInActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            btLogin.setOnClickListener {
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }


            // 해당 editText focus로 이동시키는 부분
            etLoginId.setOnClickListener { v -> v.requestFocus() }
            etLoginPw.setOnClickListener { v -> v.requestFocus() }
        }
    }
}
