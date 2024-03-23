package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.ChatRoomDetailDto
import com.goorm.kkiri.domain.model.response.MessageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {

    @GET("/messages/{chatRoomId}")
    fun getChatRoomId(
        @Path("chatRoomId") chatRoomId: String
    ): BaseResponse<MessageDto>

    @GET("/api/chatroom/board")
    fun getChatRoomByBoardId(
        @Query("boardId") boardId: Long,
        @Query("senderId") senderId: Long
    ): BaseResponse<ChatRoomDetailDto>
}