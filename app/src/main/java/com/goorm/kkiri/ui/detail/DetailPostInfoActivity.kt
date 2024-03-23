package com.goorm.kkiri.ui.detail

import android.content.Intent
import android.os.Build
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.ActivityDetailPostInfoBinding
import com.goorm.kkiri.ui.chatting.ChattingRoomFragment
import com.goorm.kkiri.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPostInfoActivity : BaseActivity<ActivityDetailPostInfoBinding>(R.layout.activity_detail_post_info) {
    private val args by navArgs<DetailPostInfoActivityArgs>()

    private val viewModel by viewModels<DetailViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
        setBackButton()
        viewModel.getBoardDetail(args.postId)
        setBoardDetail()
        clickChatBtn()
    }

    private fun setBackButton() {
        binding.toolbarDetailPost.setNavigationOnClickListener {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clickChatBtn() {
        binding.btnPostDetailChattingButton.setOnClickListener {
            val intent = Intent(this@DetailPostInfoActivity, ChattingRoomFragment::class.java)
            intent.putExtra("boardId", args.postId)
            startActivity(intent)
        }
    }

    private fun setBoardDetail() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.boardDetail.collectLatest {
                    if (it.status == "OK") {
                        binding.boardDetail = it.result
                        binding.chattingCount = args.chattingCount
                    }
                }
            }
        }
    }
}