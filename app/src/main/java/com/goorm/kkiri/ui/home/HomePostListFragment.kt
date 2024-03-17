package com.goorm.kkiri.ui.home

import android.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHomePostListBinding
import com.goorm.kkiri.ui.common.PostType
import com.goorm.kkiri.ui.common.PostType.*

class HomePostListFragment : BaseFragment<FragmentHomePostListBinding>(R.layout.fragment_home_post_list) {
    private var postType: PostType? = null

    override fun setLayout() {
        setClickListener()
    }

    private fun setClickListener() {
        with(binding) {
            toolbarHomePostList.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            ibPostWriteAddButton.setOnClickListener {
                val contentWThemeWrapper = ContextThemeWrapper(requireContext(), R.style.PopupMenuStyle)

                val popupMenu = PopupMenu(contentWThemeWrapper, it)
                popupMenu.menuInflater.inflate(R.menu.post_type_menu, popupMenu.menu)
                popupMenu.setForceShowIcon(true)
                popupMenu.show()
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_help_me -> {
                            postType =  HelpMe
                            val action = HomePostListFragmentDirections.actionHomePostListToHomeWritePost(postType!!)
                            findNavController().navigate(action)
                            return@setOnMenuItemClickListener true
                        }
                        R.id.menu_help_you -> {
                            postType = HelpYou
                            val action = HomePostListFragmentDirections.actionHomePostListToHomeWritePost(postType!!)
                            findNavController().navigate(action)
                            return@setOnMenuItemClickListener true
                        }
                        else -> return@setOnMenuItemClickListener false
                    }
                }
            }
        }
    }
}