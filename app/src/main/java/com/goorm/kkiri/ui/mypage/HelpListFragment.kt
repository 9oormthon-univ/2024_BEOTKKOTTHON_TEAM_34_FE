package com.goorm.kkiri.ui.mypage

import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHelpListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter

class HelpListFragment : BaseFragment<FragmentHelpListBinding>(R.layout.fragment_help_list),MenuClickListener {
    @RequiresApi(Build.VERSION_CODES.O)
    private val myWrittenHelpAdapter = MyWrittenHelpAdapter(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
        binding.recyclerHelp.adapter = myWrittenHelpAdapter
    }

    override fun menuClickListener(position: Long) {
        TODO("Not yet implemented")
    }
}