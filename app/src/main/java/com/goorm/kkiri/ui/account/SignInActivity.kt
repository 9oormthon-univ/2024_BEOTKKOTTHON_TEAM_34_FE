package com.goorm.kkiri.ui.account

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.goorm.kkiri.R
import com.goorm.kkiri.databinding.ActivitySignInBinding
import com.goorm.kkiri.ui.MainActivity
import com.goorm.kkiri.ui.account.viewmodel.MemberViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding>(R.layout.activity_sign_in) {
    private val viewModel by viewModels<MemberViewModel>()

    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            tvBtRegister.setOnClickListener {
                viewModel.login("sim", "1234")
                val intent = Intent(this@SignInActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            btLogin.setOnClickListener {
                viewModel.login("sim", "1234")
                login()
            }
            // 해당 editText focus로 이동시키는 부분
            etLoginId.setOnClickListener { v -> v.requestFocus() }
            etLoginPw.setOnClickListener { v -> v.requestFocus() }
        }
    }

    private fun login() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginResult.collectLatest {
                    if (it.status == "OK") {
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }
}
