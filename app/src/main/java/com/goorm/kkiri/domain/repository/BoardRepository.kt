package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardDetailDto
import com.goorm.kkiri.domain.model.response.BoardHomeDto
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.model.response.MyResult
import kotlinx.coroutines.flow.Flow

interface BoardRepository {

    suspend fun getBoardByPage(type: String, page: Int): Flow<BaseResponse<BoardPageDto>>
    suspend fun getBoardInfo(boardId: Long): Flow<BaseResponse<BoardDetailDto>>
    suspend fun getHomeBard(type: String, page: Int): Flow<BaseResponse<List<BoardHomeDto>>>
    suspend fun getMyWrittenBoard(userId: Long, type: String, page: Int): Flow<BaseResponse<MyResult>>
    suspend fun searchBoard(title: String, description: String, page: Int): Flow<BaseResponse<BoardPageDto>>
}