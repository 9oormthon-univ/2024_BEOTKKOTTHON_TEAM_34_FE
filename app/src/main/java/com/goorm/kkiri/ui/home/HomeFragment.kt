package com.goorm.kkiri.ui.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHomeBinding
import com.goorm.kkiri.ui.common.HelpType.*
import com.goorm.kkiri.ui.home.adapter.HomeHelpMeListAdapter
import com.goorm.kkiri.ui.home.adapter.HomeHelpYouListAdapter
import com.goorm.kkiri.ui.home.adapter.HomeVolunteerListAdapter
import com.goorm.kkiri.ui.home.viewmodel.BoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val helpMeAdapter = HomeHelpMeListAdapter()
    private val helpYouAdapter = HomeHelpYouListAdapter()
    private val adapter = HomeVolunteerListAdapter()
    private val viewModel by viewModels<BoardViewModel>()

    override fun setLayout() {
        viewModel.getHomeUserInfo()
        viewModel.getHomeBoardHelpMe(HELPED.name, 0)
        viewModel.getHomeBoardHelpYou(HELPING.name, 0)
        setClickListener()
        initVolunteerBanner()
        initHomeHelpBoard()
        setUserInfo()
    }

    private fun setUserInfo() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.homeUserInfoDto.collectLatest {
                    if (it.status == "OK") {
                        binding.userInfo = it.result
                    }
                }
            }
        }
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

    private fun initHomeHelpBoard() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.homeBoardHelpMe.collectLatest {
                    if (it.status == "OK") {
                        if (it.result!!.isNotEmpty()) {
                            binding.tvHomeEmptyHelpMe.visibility = View.GONE
                            helpMeAdapter.update(it.result)
                            binding.rvHelpMeList.adapter = helpMeAdapter
                        } else {
                            binding.tvHomeEmptyHelpMe.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.homeBoardHelpYou.collectLatest {
                    if (it.status == "OK") {
                        if (it.result!!.isNotEmpty()) {
                            binding.tvHomeEmptyHelpYou.visibility = View.GONE
                            helpYouAdapter.update(it.result)
                            binding.rvHelpYouList.adapter = helpYouAdapter
                        } else {
                            binding.tvHomeEmptyHelpYou.visibility = View.VISIBLE
                        }
                    }
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