package com.goorm.kkiri.domain.model.response

data class ChatRoomDetailDto(
    val otherNickname: String,
    val roomId: Long,
    val state: String,
    val boardId: Long
)
