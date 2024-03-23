package com.goorm.kkiri.ui.home

import android.view.ContextThemeWrapper
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHomePostListBinding
import com.goorm.kkiri.ui.common.HelpPostClickListener
import com.goorm.kkiri.ui.common.HelpType
import com.goorm.kkiri.ui.common.HelpType.*
import com.goorm.kkiri.ui.common.PostType
import com.goorm.kkiri.ui.common.PostType.*
import com.goorm.kkiri.ui.home.adapter.HomePostListAdapter
import com.goorm.kkiri.ui.home.viewmodel.BoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePostListFragment
    : BaseFragment<FragmentHomePostListBinding>(R.layout.fragment_home_post_list),
    HelpPostClickListener {
    private var postType: PostType? = null
    private var isHelpMeValue = false
    private var isHelpYouValue = false
    private val viewModel by viewModels<BoardViewModel>()
    private val args by navArgs<HomePostListFragmentArgs>()
    private var page = 0

    override fun setLayout() {
        binding.vm = viewModel
        setFirstHelpTextViewBackgroundTint()
        setClickListener()
        changeHelpButton()
    }

    private fun setFirstHelpTextViewBackgroundTint() {
        with(binding) {
            when (args.helpType) {
                HELPED -> {
                    isHelpMeValue = true
                    isHelpYouValue = false
                    isHelpMe = isHelpMeValue
                    isHelpYou = isHelpYouValue
                    tvHomePostListHelpMe.setTextColor(resources.getColor(R.color.white))
                    tvHomePostListHelpYou.setTextColor(resources.getColor(R.color.black))
                    initAdapter(args.helpType)
                }

                HELPING -> {
                    isHelpMeValue = false
                    isHelpYouValue = true
                    isHelpMe = isHelpMeValue
                    isHelpYou = isHelpYouValue
                    tvHomePostListHelpYou.setTextColor(resources.getColor(R.color.white))
                    tvHomePostListHelpMe.setTextColor(resources.getColor(R.color.black))
                    ivHomePostListHelpMeIcon.setImageResource(R.drawable.ic_help_me_black)
                    ivHomePostListHelpYouIcon.setImageResource(R.drawable.ic_help_you_white_checked)
                    initAdapter(args.helpType)
                }
            }
        }
    }

    private fun changeHelpButton() {
        with(binding) {
            tvHomePostListHelpMe.setOnClickListener {
                if (!isHelpMeValue) {
                    isHelpMeValue = true
                    isHelpYouValue = false
                    ivHomePostListHelpMeIcon.setImageResource(R.drawable.ic_help_me_white_checked)
                    isHelpMe = isHelpMeValue
                    isHelpYou = isHelpYouValue
                    ivHomePostListHelpYouIcon.setImageResource(R.drawable.ic_help_you_black)
                    tvHomePostListHelpMe.setTextColor(resources.getColor(R.color.white))
                    tvHomePostListHelpYou.setTextColor(resources.getColor(R.color.black))
                    initAdapter(HELPED)
                }
            }
            tvHomePostListHelpYou.setOnClickListener {
                if (!isHelpYouValue) {
                    isHelpMeValue = false
                    isHelpYouValue = true
                    ivHomePostListHelpMeIcon.setImageResource(R.drawable.ic_help_me_black)
                    ivHomePostListHelpYouIcon.setImageResource(R.drawable.ic_help_you_white_checked)
                    isHelpMe = isHelpMeValue
                    isHelpYou = isHelpYouValue
                    tvHomePostListHelpYou.setTextColor(resources.getColor(R.color.white))
                    tvHomePostListHelpMe.setTextColor(resources.getColor(R.color.black))
                    initAdapter(HELPING)
                }
            }
        }
    }

    private fun initAdapter(helpType: HelpType) {
        val adapter = HomePostListAdapter(this)
        viewModel.getBoardByPage(helpType.name, 0)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.boardList.collectLatest {
                    adapter.update(it.list)
                }
            }
        }
        binding.rvHomePostHelpList.adapter = adapter
    }

    private fun setClickListener() {
        clickPlusButton()
        clickBackIcon()
    }

    private fun clickBackIcon() {
        binding.toolbarHomePostList.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun clickPlusButton() {
        binding.ibPostWriteAddButton.setOnClickListener {
            val contentWThemeWrapper =
                ContextThemeWrapper(requireContext(), R.style.PopupMenuStyle)

            val popupMenu = PopupMenu(contentWThemeWrapper, it)
            popupMenu.menuInflater.inflate(R.menu.post_type_menu, popupMenu.menu)
            popupMenu.setForceShowIcon(true)
            popupMenu.show()
            clickPopupMenu(popupMenu)
        }
    }

    private fun clickPopupMenu(popupMenu: PopupMenu) {
        binding.ibPostWriteAddButton.setImageResource(R.drawable.ic_x_icon)
        popupMenu.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener selectMenu(it)
        }
        popupMenu.setOnDismissListener {
            binding.ibPostWriteAddButton.setImageResource(R.drawable.ic_plus_icon)
        }
    }

    private fun selectMenu(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.menu_help_me -> {
                postType = HelpMe
                val action =
                    HomePostListFragmentDirections.actionHomePostListToHomeWritePost(
                        postType!!
                    )
                findNavController().navigate(action)
                return true
            }

            R.id.menu_help_you -> {
                postType = HelpYou
                val action =
                    HomePostListFragmentDirections.actionHomePostListToHomeWritePost(
                        postType!!
                    )
                findNavController().navigate(action)
                return true
            }

            else -> {
                return false
            }
        }
    }

    override fun onHelpPostClickListener(postId: Long) {
        val action = HomePostListFragmentDirections.actionHomePostListToDetailPostInfo(postId)
        findNavController().navigate(action)
    }
}