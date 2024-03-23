package com.goorm.kkiri.domain.model.response

data class BoardVolunteerDto(
    val id: Long,
    val title: String,
    val imageUrl: Int,
    val beansCount: Int,
    val limitDate: String
)