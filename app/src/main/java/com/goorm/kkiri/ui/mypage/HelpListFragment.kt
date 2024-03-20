package com.goorm.kkiri.ui.mypage

import android.content.Intent
import android.os.Build
import android.provider.ContactsContract.Data
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
import com.goorm.kkiri.databinding.FragmentHelpListBinding
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel

@RequiresApi(Build.VERSION_CODES.O)
class HelpListFragment : BaseFragment<FragmentHelpListBinding>(R.layout.fragment_help_list),
    MenuClickListener {

    private val viewModel: ImWriteViewModel by activityViewModels()
    private lateinit var helpAdapter: MyWrittenHelpAdapter


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
        helpAdapter = MyWrittenHelpAdapter(this)
        helpAdapter.update(DataSource.writtenItems)
        binding.recyclerHelp.adapter = helpAdapter
        viewModel.helpItems.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                emptyListIntro(true) // 리스트가 비었을 때 안내문 보이기
            } else {
                emptyListIntro(false) // 리스트에 데이터가 있을 때 안내문 숨기기

            }

            helpAdapter.update(items)
        }

    }
    private fun emptyListIntro(state : Boolean){
        binding.tvEmptyHelpInstructions.isVisible = state
    }
    private fun setTopButton(){
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        binding.recyclerHelp.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerHelp.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.fbHelpTopBtn.startAnimation(fadeOut)
                    binding.fbHelpTopBtn.visibility = View.GONE
                    isTop = true
                } else {
                    if(isTop) {
                        binding.fbHelpTopBtn.visibility = View.VISIBLE
                        binding.fbHelpTopBtn.startAnimation(fadeIn)
                        isTop = false
                    }
                }
            }
        })

        binding.fbHelpTopBtn.setOnClickListener {
            binding.recyclerHelp.smoothScrollToPosition(0)
        }
    }


    //메뉴를 누르면 이동
    override fun menuClickListener(position: Long) {
        startActivity(Intent(requireContext(), DetailImWriteActivity::class.java))
    }
}
