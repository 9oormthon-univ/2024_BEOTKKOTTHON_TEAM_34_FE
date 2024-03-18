package com.goorm.kkiri.ui.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goorm.kkiri.R
import com.goorm.kkiri.ui.mypage.IwViewPagerFragment

class IwViewPagerAdapter(fm : FragmentActivity) : FragmentStateAdapter(fm){
    override fun getItemCount(): Int {
        // 생성할 Fragment의 개수를 반환
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        // 각 포지션에 맞는 Fragment를 반환
        return when (position) {
            0 ->  IwViewPagerFragment.newInstance(R.mipmap.image_iw_sample)
            1 -> IwViewPagerFragment.newInstance(R.mipmap.image_iw_sample)
            else -> IwViewPagerFragment.newInstance(R.mipmap.image_iw_sample)
        }
    }

}