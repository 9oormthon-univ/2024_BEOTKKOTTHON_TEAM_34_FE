package com.goorm.kkiri.domain.model.response

data class BoardIndividualDto(
    val id: Long,
    val title: String,
    val point: Int,
    val chatRoomsCount: Int,
    val createdDate: String,
    val type: String,
    val attachmentOutputDto: String?
)
