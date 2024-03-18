package com.goorm.kkiri.domain.model.response

data class PostItem(
    val postId: Long,
    val postTitle: String,
    val imageUrl: Int?,
    val createAt: String,
    val beansCount: Int,
    val chattingCount: Int
)
