package com.goorm.kkiri.domain.model.response

data class UserDto(
    val id: Int,
    val username: String,
    val password: String,
    val nickname: String,
    val univ: String,
    val point: Int,
    val consumePoint: Int,
    val earnPoint: Int,
    val boards: List<Board>,
    val chatSendRooms: List<ChatRoom>,
    val chatReceiveRooms: List<ChatRoom>
)