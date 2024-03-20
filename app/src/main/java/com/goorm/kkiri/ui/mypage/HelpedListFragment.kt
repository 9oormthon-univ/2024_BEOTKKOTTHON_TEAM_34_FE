package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentHelpedListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel


@RequiresApi(Build.VERSION_CODES.O)
class HelpedListFragment : BaseFragment<FragmentHelpedListBinding>(R.layout.fragment_helped_list),
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
        binding.recyclerHelped.adapter = myWrittenHelpAdapter
    }
    //뷰 모델 옵저버, 스크롤 이벤트 장착
    private fun observeViewModel() {
        viewModel.itemList.observe(viewLifecycleOwner) { items ->
            myWrittenHelpAdapter.update(items)
        }

        viewModel.scrollToTopEvent.observe(viewLifecycleOwner) {
            scrollToTop()
        }

        binding.recyclerHelped.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                handleScrollStateChange(newState)
            }
        })
    } private fun handleScrollStateChange(newState: Int) {
        val recyclerView = binding.recyclerHelped
        val canScrollUp = recyclerView.canScrollVertically(-1)
        viewModel.setFloatButtonVisibility(canScrollUp || newState != RecyclerView.SCROLL_STATE_IDLE)
    }

    //스크롤을 맨 위로 올리는 함수
    private fun scrollToTop() {
        binding.recyclerHelped.smoothScrollToPosition(0)
    }

    //메뉴를 누르면 이동
    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}