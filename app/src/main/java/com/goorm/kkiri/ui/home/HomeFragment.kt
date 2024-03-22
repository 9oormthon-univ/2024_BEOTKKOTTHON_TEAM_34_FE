package com.goorm.kkiri.ui.home

import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHomeBinding
import com.goorm.kkiri.ui.common.PostType.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            tvHomeMoreHelpMe.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToHomePostList(HelpMe)
                findNavController().navigate(action)
            }
            tvHomeMoreHelpYou.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToHomePostList(HelpYou)
                findNavController().navigate(action)
            }
            toolbarHome.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_serch -> {
                        val action = HomeFragmentDirections.actionHomeToSearch()
                        findNavController().navigate(action)
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener  true
                }
            }
        }
    }
}