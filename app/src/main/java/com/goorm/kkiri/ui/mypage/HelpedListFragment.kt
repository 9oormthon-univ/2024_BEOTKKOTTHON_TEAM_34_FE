package com.goorm.kkiri.ui.mypage

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentHelpedListBinding
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpAdapter
import com.goorm.kkiri.ui.mypage.adapter.MyWrittenHelpedAdapter
import com.goorm.kkiri.ui.mypage.viewmodel.ImWriteViewModel
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class HelpedListFragment : BaseFragment<FragmentHelpedListBinding>(R.layout.fragment_helped_list),
    MenuClickListener {
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    private val viewModel: ImWriteViewModel by activityViewModels()
    private lateinit var helpedAdapter: MyWrittenHelpedAdapter
    private var selectPos = -1L
    override fun setLayout() {
        initAdapter()
        resultBackToTheDetailImWrite()

    }

    //어댑터 초기화
    private fun initAdapter() {
        setupRecyclerView()
        setTopButton()
    }

    //리사이클러뷰 세팅
    private fun setupRecyclerView() {
        helpedAdapter = MyWrittenHelpedAdapter(this)
        helpedAdapter.update(DataSource.writtenItems2)
        binding.recyclerHelped.adapter = helpedAdapter
        viewModel.helpedItems.observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                emptyListIntro(true) // 리스트가 비었을 때 안내문 보이기
            } else {
                emptyListIntro(false) // 리스트에 데이터가 있을 때 안내문 숨기기
            }
            helpedAdapter.update(items)
        }

    }

    private fun emptyListIntro(state: Boolean) {
        binding.tvEmptyHelpedInstructions.isVisible = state
    }

    private fun setTopButton() {
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        binding.recyclerHelped.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerHelped.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.fbHelpedTopBtn.startAnimation(fadeOut)
                    binding.fbHelpedTopBtn.visibility = View.GONE
                    isTop = true
                } else {
                    if (isTop) {
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
        val intent = Intent(requireContext(), DetailImWriteActivity::class.java).apply {
            putExtra("helpTt", viewModel.fetchHelpedDate(position)?.titleWt)
            putExtra("helpDt", viewModel.fetchHelpedDate(position)?.dateWt.toString())
            putExtra("helpImg", viewModel.fetchHelpedDate(position)?.imgWt.toString())
            putExtra("helpExp", viewModel.fetchHelpedDate(position)?.expWt)
            putExtra("helpBc", viewModel.fetchHelpedDate(position)?.beenCountWt)
            selectPos = position
            //추후 서버에서 가저올 값들
        }
        startForResult.launch(intent)

    }
    private fun resultBackToTheDetailImWrite() {
        startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // 결과 처리
                    val data: Intent? = result.data
                    Log.d("확인2", "$selectPos}")

                    // 데이터 사용, 예: data.getStringExtra("resultKey")
                    if (data != null && data.getStringExtra("return") == "ok") {
                        // 새 MyWrittenMenuItem 인스턴스 생성
                        val newItem = MyWrittenMenuItem(
                            recordIdWt = selectPos,  // selectPos를 식별자로 사용 (적절히 수정해야 할 수 있음)
                            beenCountWt = data.getStringExtra("rtHelpBc")?.toLong() ?: 0L,
                            dateWt = LocalDate.now(),  // 날짜는 예시입니다. 실제 데이터로 대체 필요
                            titleWt = data.getStringExtra("rtHelpTt").toString(),
                            imgWt = null,  // 이미지 처리 필요
                            expWt = data.getStringExtra("rtHelpExp").toString()
                        )
                        viewModel.updateHelpedList(selectPos.toInt(), newItem)
                    }
                }
            }
    }
}