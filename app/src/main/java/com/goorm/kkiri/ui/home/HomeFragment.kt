package com.goorm.kkiri.ui.home

import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHomeBinding
import com.goorm.kkiri.ui.common.HelpType.*
import com.goorm.kkiri.ui.home.adapter.HomeVolunteerListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val adapter = HomeVolunteerListAdapter()

    override fun setLayout() {
        setClickListener()
        initVolunteerBanner()
    }

    private fun setClickListener() {
        with(binding) {
            tvHomeMoreHelpMe.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToHomePostList(HELPED)
                findNavController().navigate(action)
            }
            tvHomeMoreHelpYou.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToHomePostList(HELPING)
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

    private fun initVolunteerBanner() {
        DataSource.initBoardVolunteerList()
        val result = DataSource.boardVolunteerDto
        adapter.update(result)
        binding.rvVolunteerList.adapter = adapter
    }
}