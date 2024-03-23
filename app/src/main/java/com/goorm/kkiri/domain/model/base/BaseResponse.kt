package com.goorm.kkiri.domain.model.base

data class BaseResponse <T> (
    val status: String,
    val message: String,
    val result: T
)