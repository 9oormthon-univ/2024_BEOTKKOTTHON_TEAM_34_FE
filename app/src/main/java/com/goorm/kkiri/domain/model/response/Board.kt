package com.goorm.kkiri.domain.model.response

data class Board(
    val createdDate: String,
    val lastModifiedDate: String,
    val id: Int,
    val title: String,
    val description: String,
    val type: String,
    val state: String,
    val exchangePoint: Int,
    val member: String,
    val attachments: List<Attachment>,
    val chatRooms: List<ChatRoom>
)