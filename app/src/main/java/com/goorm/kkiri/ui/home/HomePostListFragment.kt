package com.goorm.kkiri.ui.home

import android.view.ContextThemeWrapper
import android.view.MenuItem
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHomePostListBinding
import com.goorm.kkiri.ui.common.HelpPostClickListener
import com.goorm.kkiri.ui.common.PostType
import com.goorm.kkiri.ui.common.PostType.*
import com.goorm.kkiri.ui.home.adapter.HomePostListAdapter

class HomePostListFragment
    : BaseFragment<FragmentHomePostListBinding>(R.layout.fragment_home_post_list),
    HelpPostClickListener {
    private var postType: PostType? = null

    override fun setLayout() {
        setClickListener()
        initAdapter()
    }

    private fun initAdapter() {
        val adapter = HomePostListAdapter(this)
        DataSource.initHelpMePostItems()
        adapter.update(DataSource.postItems)
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