package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHelpedListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel


@RequiresApi(Build.VERSION_CODES.O)
class HelpedListFragment : BaseFragment<FragmentHelpedListBinding>(R.layout.fragment_helped_list),
    MenuClickListener {
    private val myWrittenHelpedAdapter = MyWrittenHelpAdapter(this)
    private val viewModel: ImWriteViewModel by activityViewModels()

    override fun setLayout() {

        // ViewModel 초기화
        binding.recyclerHelped.adapter = myWrittenHelpedAdapter
        viewModel.itemList.observe(viewLifecycleOwner) { items ->
            // 어댑터에 데이터 업데이트
            myWrittenHelpedAdapter.update(items)
        }
    }

    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}