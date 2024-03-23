package com.goorm.kkiri.ui.detail

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.ActivityDetailPostInfoBinding
import com.goorm.kkiri.ui.detail.viewmodel.DetailViewModel
import com.goorm.kkiri.ui.mypage.IwViewPagerFragment
import com.goorm.kkiri.ui.mypage.adapter.IwViewPagerAdapter
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
        binding.ivDetailPostImage.adapter = ScreenSlidePagerAdapter(this)
        binding.ivDetailPostImage.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.icViewPager.setViewPager(binding.ivDetailPostImage)
    }

    private fun setBackButton() {
        binding.toolbarDetailPost.setNavigationOnClickListener {
            finish()
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = itemCount
        /*viewModel.boardDetail.value.result?.images?.size!! // 페이지 수 리턴*/
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                in 0 until itemCount -> IwViewPagerFragment.newInstance(R.mipmap.image_iw_sample)
                else -> throw IllegalStateException("Invalid position")
            }
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