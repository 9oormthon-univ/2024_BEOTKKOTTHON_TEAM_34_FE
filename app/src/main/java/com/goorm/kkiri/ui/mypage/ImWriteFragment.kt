package com.goorm.kkiri.ui.mypage

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
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
                tab.text = tabItems[position].title
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
