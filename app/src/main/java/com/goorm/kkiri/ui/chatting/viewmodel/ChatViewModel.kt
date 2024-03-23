package com.goorm.kkiri.ui.chatting.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.ChatRoomDetailDto
import com.goorm.kkiri.domain.repository.ChattingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chattingRepository: ChattingRepository
) : ViewModel() {

    private var _chatRoomDetail = MutableStateFlow(BaseResponse<ChatRoomDetailDto>())
    val chatRoomDetail: StateFlow<BaseResponse<ChatRoomDetailDto>> = _chatRoomDetail

    fun getChatRoomDetailByRoomId(boardId: Long, senderId: Long) {
        viewModelScope.launch {
            try {
                chattingRepository.getChatRoomByBoardId(boardId, senderId).collect {
                    _chatRoomDetail.value = it
                }
            } catch (e: Exception) {
                Log.e("Chat Room Error", e.message.toString())
            }
        }
    }
}