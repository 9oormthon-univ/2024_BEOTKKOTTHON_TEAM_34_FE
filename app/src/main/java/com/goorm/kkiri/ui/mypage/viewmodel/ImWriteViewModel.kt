package com.goorm.kkiri.ui.mypage.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.goorm.kkiri.ui.mypage.fragment.HelpListFragment
import com.goorm.kkiri.ui.mypage.fragment.HelpedListFragment

data class TabItem(val title: String, val fragment: Fragment)
class ImWriteViewModel : ViewModel() {

    private val tabItems = listOf(
        TabItem("도와주세요!!", HelpListFragment()),
        TabItem("도와줄게요!!", HelpedListFragment())
    )
    fun getTabCount() = tabItems.size
    fun getTabTitle(position: Int) = tabItems[position].title
    fun getTabFragment(position: Int) = tabItems[position].fragment
}