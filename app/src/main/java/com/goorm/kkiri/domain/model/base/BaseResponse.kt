package com.goorm.kkiri.domain.model.base

data class BaseResponse <T> (
    val status: Int,
    val message: String,
    val result: T
)