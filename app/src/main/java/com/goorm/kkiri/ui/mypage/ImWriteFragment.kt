package com.goorm.kkiri.ui.mypage

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentImWriteBinding
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel

class ImWriteFragment : BaseFragment<FragmentImWriteBinding>(R.layout.fragment_im_write) {
    //뷰
    private lateinit var viewModel: ImWriteViewModel
    override fun setLayout() {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewModel = ViewModelProvider(this)[ImWriteViewModel::class.java]
        val pagerAdapter = MyPagerAdapter(this)
        binding.imWritePager.adapter = pagerAdapter
        TabLayoutMediator(binding.imWriteTab, binding.imWritePager) { tab, position ->
            tab.text = viewModel.getTabTitle(position)
        }.attach()
    }


    private inner class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = viewModel.getTabCount()
        override fun createFragment(position: Int): Fragment {
            return viewModel.getTabFragment(position)
        }
    }
}
