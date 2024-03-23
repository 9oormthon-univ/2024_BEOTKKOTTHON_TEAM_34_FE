package com.goorm.kkiri.ui.chatting

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.goorm.kkiri.R
import com.goorm.kkiri.base.BaseActivity
import com.goorm.kkiri.base.BaseFragment
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.databinding.FragmentChattingMainBinding
import com.goorm.kkiri.databinding.FragmentChattingRoomBinding
import com.goorm.kkiri.domain.model.response.ChatRoomItem
import com.goorm.kkiri.ui.chatting.adapter.ChatMainAdapter
import com.goorm.kkiri.ui.chatting.adapter.ChatRoomAdapter
import com.goorm.kkiri.ui.chatting.adapter.SpinnerAdapter
import com.goorm.kkiri.ui.chatting.viewmodel.ChatViewModel
import com.goorm.kkiri.ui.mypage.adapter.MenuClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class ChattingRoomFragment :
    BaseActivity<FragmentChattingRoomBinding>(R.layout.fragment_chatting_room) {
    private lateinit var spinner: Spinner
    private lateinit var adapterSpinner: SpinnerAdapter
    private lateinit var adapter : ChatRoomAdapter
    private val viewModel by viewModels<ChatViewModel>()

    private var isState = false
    private var count = 0

    override fun setLayout() {
        initAdapter()
        initSpinner()
        clickBackIcon()
    }

    private fun initAdapter() {
        adapter = ChatRoomAdapter()
        DataSource.initChatRoomMenuItems()
        adapter.update(DataSource.chatRoomItems)
        binding.rvChatRoom.adapter = adapter
    }

    private fun initSpinner() {
        spinner = binding.spTransactionStart
        val spState = arrayListOf("거래 전", "거래중", "거래완료")
        adapterSpinner = SpinnerAdapter(this, spState) // 수정된 리스트로 어댑터 초기화
        spinner.adapter = adapterSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무것도 선택되지 않았을 때의 동작 (필요한 경우 구현)
            }
        }
    }

    private fun extractTime(localDateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return localDateTime.format(formatter)
    }

    private fun clickBackIcon() {

        binding.toolbarChatReceiver.setNavigationOnClickListener {
            finish()
        }
        binding.btSendMessage.setOnClickListener{

            //서버에서 값 받아옴,
            isState = !isState
            count++
            val exp = binding.etMessageSendField.text
            val lt = LocalDateTime.now()
            val flt = extractTime(lt)
            val cri = ChatRoomItem(count.toLong(),"","","$exp",flt,isState)
            DataSource.chatRoomItems.add(cri)
            adapter.update(DataSource.chatRoomItems)
            Log.d("isState",isState.toString())

        }
    }
}