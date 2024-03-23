package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
import com.goorm.kkiri.domain.model.response.BoardDetailDto
import com.goorm.kkiri.domain.model.response.BoardPageDto
import kotlinx.coroutines.flow.Flow

interface BoardRepository {

    suspend fun getBoardByPage(type: String, page: Int): Flow<BaseResponse<BoardPageDto>>
    suspend fun getBoardInfo(boardId: Long): Flow<BaseResponse<BoardDetailDto>>
}