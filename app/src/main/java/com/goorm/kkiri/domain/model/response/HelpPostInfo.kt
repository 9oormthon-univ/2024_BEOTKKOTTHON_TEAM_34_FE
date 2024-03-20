package com.goorm.kkiri.domain.model.response

data class HelpPostInfo(
    val postId: Long,
    val nickname: String,
    val postTitle: String,
    val content: String,
    val imageUrl: Int?,
    val createAt: String,
    val beansCount: Int,
    val chattingCount: Int
)