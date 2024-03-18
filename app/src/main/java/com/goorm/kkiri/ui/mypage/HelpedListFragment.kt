package com.goorm.kkiri.ui.mypage
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHelpedListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter

class HelpedListFragment : BaseFragment<FragmentHelpedListBinding>(R.layout.fragment_helped_list) ,
    MenuClickListener {
    @RequiresApi(Build.VERSION_CODES.O)
    private val myWrittenHelpedAdapter = MyWrittenHelpAdapter(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setLayout() {
        binding.recyclerHelped.adapter = myWrittenHelpedAdapter
    }

    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext() , DetailImWriteActivity::class.java))
    }
}