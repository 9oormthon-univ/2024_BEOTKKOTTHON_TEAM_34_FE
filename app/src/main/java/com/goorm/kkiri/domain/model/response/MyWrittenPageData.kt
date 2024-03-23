package com.goorm.kkiri.domain.model.response

data class MyResult(
    val currentPage: Int = 0,
    var list: List<MyPost> = emptyList()
)


