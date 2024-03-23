package com.goorm.kkiri.ui.detail

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.ActivityDetailPostInfoBinding
import com.goorm.kkiri.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPostInfoActivity : BaseActivity<ActivityDetailPostInfoBinding>(R.layout.activity_detail_post_info) {
    private val args by navArgs<DetailPostInfoActivityArgs>()

    private val viewModel by viewModels<DetailViewModel>()

    override fun setLayout() {
        setBackButton()
        viewModel.getBoardDetail(args.postId)
        setBoardDetail()
    }

    private fun setBackButton() {
        binding.toolbarDetailPost.setNavigationOnClickListener {
            finish()
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