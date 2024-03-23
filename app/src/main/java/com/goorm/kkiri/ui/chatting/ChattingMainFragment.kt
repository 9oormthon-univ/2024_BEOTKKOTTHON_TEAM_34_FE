package com.goorm.kkiri.ui.chatting

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentChattingMainBinding
import com.goorm.kkiri.ui.chatting.adapter.ChatMainAdapter
import com.goorm.kkiri.ui.home.adapter.HomePostListAdapter
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener

@RequiresApi(Build.VERSION_CODES.O)
class ChattingMainFragment : BaseFragment<FragmentChattingMainBinding>(R.layout.fragment_chatting_main),MenuClickListener{

    override fun setLayout() {
        initAdapter()
    }

    private fun initAdapter(){
        val adapter = ChatMainAdapter(this)
        DataSource.initChatMainMenuItems()
        adapter.update(DataSource.chatMainItems)
        binding.rvChatMain.adapter = adapter
    }

    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(),ChattingRoomFragment::class.java))
    }

}