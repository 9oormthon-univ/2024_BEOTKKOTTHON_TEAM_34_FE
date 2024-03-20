package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHelpListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel

@RequiresApi(Build.VERSION_CODES.O)
class HelpListFragment : BaseFragment<FragmentHelpListBinding>(R.layout.fragment_help_list),
    MenuClickListener {
    private val myWrittenHelpAdapter = MyWrittenHelpAdapter(this)

    private val viewModel: ImWriteViewModel by activityViewModels()
    override fun setLayout() {
        initAdapter()
    }
    private fun initAdapter(){
        DataSource.initHelpMePostItems()
        myWrittenHelpAdapter.update(DataSource.writtemItems.toList())
        binding.recyclerHelp.adapter = myWrittenHelpAdapter
        viewModel.itemList.observe(viewLifecycleOwner) { items ->
            // 어댑터에 데이터 업데이트
            myWrittenHelpAdapter.update(items)
        }
    }

    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}