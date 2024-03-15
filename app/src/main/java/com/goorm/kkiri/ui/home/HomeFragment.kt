package com.goorm.kkiri.ui.home

import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            tvHomeMoreHelpMe.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToHomePostList()
                findNavController().navigate(action)
            }
        }
    }
}