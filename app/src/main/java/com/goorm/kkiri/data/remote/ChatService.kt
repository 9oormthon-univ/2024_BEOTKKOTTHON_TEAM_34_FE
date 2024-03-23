package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.MessageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatService {

    @GET("/messages/{chatRoomId}")
    fun getChatRoomId(
        @Path("chatRoomId") chatRoomId: String
    ): BaseResponse<MessageDto>

}