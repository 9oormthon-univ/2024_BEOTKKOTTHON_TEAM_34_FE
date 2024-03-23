package com.goorm.kkiri.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.goorm.kkiri.R
import com.goorm.kkiri.databinding.ActivityIntroBinding
import com.goorm.kkiri.databinding.ActivitySplashBinding
import com.goorm.kkiri.ui.MainActivity
import com.goorm.kkiri.ui.account.SignInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Glide.with(this)
            .load(R.drawable.slash) // Make sure 'slash' is the correct GIF resource name
            .into(binding.ivSplash)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish() // Close the splash activity so the user can't return to it
        }, 2000) // 5000 milliseconds delay

    }
}
