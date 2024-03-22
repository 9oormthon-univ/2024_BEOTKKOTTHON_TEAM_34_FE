package com.goorm.kkiri.domain.model.response

data class Attachment(
    val id: Int,
    val board: String,
    val uploadFileName: String,
    val storeFileName: String
)