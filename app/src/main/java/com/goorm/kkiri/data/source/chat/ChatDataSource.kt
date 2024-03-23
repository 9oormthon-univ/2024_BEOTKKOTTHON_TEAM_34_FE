package com.goorm.kkiri.data.source.chat

import android.util.Log
import com.goorm.kkiri.data.remote.ChatService
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.ChatRoomDetailDto
import com.goorm.kkiri.domain.model.response.MessageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    private val chatService: ChatService
) {
    fun getChattingByRoomId(chatRoomId: String): Flow<BaseResponse<MessageDto>> = flow {
        val result = chatService.getChatRoomId(chatRoomId)
        emit(result)
    }.catch {
        Log.e("getChattingByRoomId",it.message.toString())
    }

    fun getChatRoomByRoomId(boardId: Long, senderId: Long): Flow<BaseResponse<ChatRoomDetailDto>> = flow {
        val result = chatService.getChatRoomByBoardId(boardId, senderId)
        emit(result)
    }.catch {
        Log.e("Get Chat Room By Room Id", it.message.toString())
    }
}