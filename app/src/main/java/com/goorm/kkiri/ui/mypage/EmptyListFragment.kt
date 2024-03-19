package com.goorm.kkiri.ui.mypage
import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentEmptyListBinding



@RequiresApi(Build.VERSION_CODES.O)
class EmptyListFragment : BaseFragment<FragmentEmptyListBinding>(R.layout.fragment_empty_list){


    override fun setLayout() {
        binding.tvEmptyInstructions.text ="""게시물이 없습니다.
            |도움 서비스를 제공해 보세요!""".trimMargin()
    }
}