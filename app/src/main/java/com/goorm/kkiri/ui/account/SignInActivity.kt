package com.goorm.kkiri.ui.account

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.goorm.kkiri.R
import com.goorm.kkiri.databinding.ActivitySignInBinding
import com.goorm.kkiri.ui.MainActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
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
