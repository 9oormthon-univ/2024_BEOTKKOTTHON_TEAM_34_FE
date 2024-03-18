package com.goorm.kkiri.domain.model.response

data class PostItem(
    val postId: Long,
    val postTitle: String,
    val imageUrl: String,
    val createAt: String,
    val beansCount: Int,
    val chattingCount: Int
)
