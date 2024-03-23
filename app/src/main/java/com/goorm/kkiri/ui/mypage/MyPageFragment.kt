package com.goorm.kkiri.ui.mypage
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentMyPageBinding
import com.goorm.kkiri.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()

    override fun setLayout() {
        //마이페이지 -> 내가 쓴 글 페이지
        setClickListener()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            viewModel.getMyPageInfo()
            setUserInfo()
        }
    }

    private fun setClickListener() {
        with(binding) {
            mpWrite.setOnClickListener {
                val action = MyPageFragmentDirections.actionMyPageToImWrite()
                findNavController().navigate(action)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUserInfo() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.myPageInfo.collectLatest {
                    if (it.status == "OK") {
                        binding.userInfo = it.result
                    }
                }
            }
        }
    }
}