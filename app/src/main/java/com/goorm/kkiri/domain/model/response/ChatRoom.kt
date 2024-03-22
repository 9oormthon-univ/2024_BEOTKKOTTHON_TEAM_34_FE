package com.goorm.kkiri.domain.model.response

data class ChatRoom(
    val createdDate: String,
    val lastModifiedDate: String,
    val id: Int,
    val board: String,
    val sender: String,
    val receiver: String,
    val latestMessage: String
)