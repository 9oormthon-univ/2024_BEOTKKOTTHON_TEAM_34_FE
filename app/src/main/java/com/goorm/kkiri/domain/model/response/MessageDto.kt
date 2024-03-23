package com.goorm.kkiri.domain.model.response


data class MessageDto(
    val id: String,
    val chatRoomId: String,
    val senderId: String,
    val recipientId: String,
    val content: String,
    val createdAt: String // 이 부분은 실제 날짜/시간 처리를 위해 LocalDateTime 등을 사용할 수 있으나 예시이므로 String 타입으로 지정
)
