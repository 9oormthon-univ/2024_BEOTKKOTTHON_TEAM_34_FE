package com.goorm.kkiri.domain.model.response

data class MyPost(
    val id: Int = 0,
    val title: String = "",
    val point: Int = 0,
    val chatRoomsCount: Int = 0,
    val createdDate: String = "",
    val type: String = "",
    val attachmentOutputDto: Attachment? = null
)