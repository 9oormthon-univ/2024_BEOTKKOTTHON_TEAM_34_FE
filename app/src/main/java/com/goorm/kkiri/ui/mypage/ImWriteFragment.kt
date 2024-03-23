package com.goorm.kkiri.ui.mypage

import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentImWriteBinding
import com.goorm.kkiri.ui.mypage.viewmodel.MyPageViewModel
import com.goorm.kkiri.ui.mypage.viewmodel.TabItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class ImWriteFragment : BaseFragment<FragmentImWriteBinding>(R.layout.fragment_im_write) {
    private val viewModel: MyPageViewModel by activityViewModels()

    override fun setLayout() {
        setupViewPagerAndTabs()
    }


    //뷰페이저에 탭 아이템 적용
    private fun setupViewPagerAndTabs() {
        viewModel.tabItems.observe(viewLifecycleOwner) { tabItems ->
            val pagerAdapter = MyPagerAdapter(this)
            binding.imWritePager.adapter = pagerAdapter
            TabLayoutMediator(binding.imWriteTab, binding.imWritePager) { tab, position ->
                setupTab(tab, tabItems[position].title)
            }.attach()
            pagerAdapter.setTabItems(tabItems)
        }
    }

    //탭 바 변경시 적용 폰트 유지
    private fun setupTab(tab: TabLayout.Tab, title: String) {

        val textView = TextView(context).apply {
            text = title
            textSize = 14f
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            if (context != null) {
                typeface = resources.getFont(R.font.nanum_square_round_bold)
            }
        }
        tab.customView = textView
    }

    //뷰페이저 어댑터
    private inner class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        private var tabItems: List<TabItem> = emptyList()

        fun setTabItems(tabItems: List<TabItem>) {
            this.tabItems = tabItems
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = tabItems.size

        override fun createFragment(position: Int): Fragment = tabItems[position].fragment
    }
}