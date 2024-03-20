package com.goorm.kkiri.ui.mypage

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentImWriteBinding
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel
import com.goorm.kkiri.ui.mypage.viewmodel.TabItem

@RequiresApi(Build.VERSION_CODES.O)
class ImWriteFragment : BaseFragment<FragmentImWriteBinding>(R.layout.fragment_im_write) {
    private val viewModel: ImWriteViewModel by activityViewModels()
    override fun setLayout() {

        setupViewPagerAndTabs()
    }

    private fun setupViewPagerAndTabs() {
        // tabItems LiveData 관찰
        viewModel.tabItems.observe(viewLifecycleOwner) { tabItems ->
            val pagerAdapter = MyPagerAdapter(this)
            binding.imWritePager.adapter = pagerAdapter
            TabLayoutMediator(binding.imWriteTab, binding.imWritePager) { tab, position ->
                // 탭에 커스텀 뷰 적용
                val textView = TextView(context).apply {
                    text = tabItems[position].title
                    textSize = 14f // 폰트 크기 설정
                    gravity = Gravity.CENTER // 텍스트 가운데 정렬
                    typeface = Typeface.DEFAULT_BOLD // 텍스트 굵게
                    if (context != null) {
                        typeface = resources.getFont(R.font.nanum_square_round_bold)
                        // 'NanumSquareRound' 폰트 적용
                    }
                }

                // 탭에 TextView를 설정
                tab.customView = textView
            }.attach()
            pagerAdapter.setTabItems(tabItems)
        }
    }

    private inner class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        private var tabItems: List<TabItem> = emptyList()

        //탭 아이템 갈아끼우고 화면 업데이트
        fun setTabItems(tabItems: List<TabItem>) {
            this.tabItems = tabItems
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = tabItems.size

        override fun createFragment(position: Int): Fragment {
            return tabItems[position].fragment
        }
    }
}
