package com.goorm.kkiri.ui.mypage
import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.databinding.FragmentEmptyListBinding



@RequiresApi(Build.VERSION_CODES.O)
class EmptyListFragment : BaseFragment<FragmentEmptyListBinding>(R.layout.fragment_empty_list){


    override fun setLayout() {
        setString("${R.string.text_info_blank_post_message}")
    }
    private fun setString(msg : String){
        with(binding){
            tvEmptyInstructions.text = getString(msg.toInt());
        }
    }
}