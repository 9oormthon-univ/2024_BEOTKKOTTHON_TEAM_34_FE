package com.goorm.kkiri.ui.home

import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHomePostListBinding

class HomePostListFragment : BaseFragment<FragmentHomePostListBinding>(R.layout.fragment_home_post_list) {
    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            toolbarHomePostList.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}