package com.goorm.kkiri.domain.model.response

data class BoardPageDto(
    val currentPage: Int = 0,
    val list: List<BoardIndividualDto> = emptyList()
)
