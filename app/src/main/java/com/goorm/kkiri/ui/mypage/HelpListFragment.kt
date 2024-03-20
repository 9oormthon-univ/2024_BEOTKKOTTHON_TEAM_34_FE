package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
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

    private val viewModel: ImWriteViewModel by activityViewModels()
    private val myWrittenHelpAdapter = MyWrittenHelpAdapter(this)


    override fun setLayout() {
        initAdapter()
    }

    //어댑터 초기화
    private fun initAdapter() {
        setupRecyclerView()
        observeViewModel()
    }
    //리사이클러뷰 세팅
    private fun setupRecyclerView() {
        binding.recyclerHelp.adapter = myWrittenHelpAdapter
    }

    //뷰 모델 옵저버, 스크롤 이벤트 장착
    private fun observeViewModel() {
        viewModel.itemList.observe(viewLifecycleOwner) { items ->
            myWrittenHelpAdapter.update(items)
        }

        viewModel.scrollToTopEvent.observe(viewLifecycleOwner) {
            scrollToTop()
        }

        binding.recyclerHelp.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                handleScrollStateChange(newState)
            }
        })
    }

    //스크롤이 멈추면 버튼의 가시성 변경
    private fun handleScrollStateChange(newState: Int) {
        val recyclerView = binding.recyclerHelp
        val canScrollUp = recyclerView.canScrollVertically(-1)
        viewModel.setFloatButtonVisibility(canScrollUp || newState != RecyclerView.SCROLL_STATE_IDLE)
    }

    //스크롤을 맨 위로 올리는 함수
    private fun scrollToTop() {
        binding.recyclerHelp.smoothScrollToPosition(0)
    }

    //메뉴를 누르면 이동
    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}
