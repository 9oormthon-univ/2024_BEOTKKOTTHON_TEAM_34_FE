package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHelpedListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel


@RequiresApi(Build.VERSION_CODES.O)
class HelpedListFragment : BaseFragment<FragmentHelpedListBinding>(R.layout.fragment_helped_list),
    MenuClickListener {
    private val viewModel: ImWriteViewModel by activityViewModels()
    private lateinit var helpedAdapter: MyWrittenHelpAdapter
    override fun setLayout() {
        initAdapter()
    }

    //어댑터 초기화
    private fun initAdapter() {
        setupRecyclerView()
        setTopButton()
    }

    //리사이클러뷰 세팅
    private fun setupRecyclerView() {
        helpedAdapter = MyWrittenHelpAdapter(this)
        helpedAdapter.update(DataSource.writtenItems2)
        binding.recyclerHelped.adapter = helpedAdapter

        // 뷰모델에서 아이템 리스트 관찰
        viewModel.helpedItems.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                emptyListIntro(true) // 리스트가 비었을 때 안내문 보이기
            } else {
                emptyListIntro(false) // 리스트에 데이터가 있을 때 안내문 숨기기
            }
            helpedAdapter.update(items)
        }

    }
    private fun emptyListIntro(state : Boolean){
        binding.tvEmptyHelpedInstructions.isVisible = state
    }
    private fun setTopButton(){
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        binding.recyclerHelped.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerHelped.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fbHelpedTopBtn.startAnimation(fadeOut)
                    binding.fbHelpedTopBtn.visibility = View.GONE
                    isTop = true
                } else {
                    if(isTop) {
                        binding.fbHelpedTopBtn.visibility = View.VISIBLE
                        binding.fbHelpedTopBtn.startAnimation(fadeIn)
                        isTop = false
                    }
                }
            }
        })

        binding.fbHelpedTopBtn.setOnClickListener {
            binding.recyclerHelped.smoothScrollToPosition(0)
        }
    }


    //메뉴를 누르면 이동
    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}