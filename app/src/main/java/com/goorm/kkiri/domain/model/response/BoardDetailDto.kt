package com.goorm.kkiri.domain.model.response

data class BoardDetailDto(
    val title: String,
    val description: String,
    val name: String,
    val time: String,
    val point: Long,
    val images: List<AttachmentOutPutDto>
)