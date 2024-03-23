package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.model.response.MyResult
import kotlinx.coroutines.flow.Flow

interface BoardRepository {
    suspend fun getBoardByPage(type: String, pageable: Pageable): Flow<BaseResponse<BoardPageDto>>
    suspend fun getMyWrittenBoard(userId: Long, type: String, page: Int): Flow<BaseResponse<MyResult>>
}