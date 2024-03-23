package com.goorm.kkiri.data.source.board

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardDetailDto
import com.goorm.kkiri.domain.model.response.BoardHomeDto
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.model.response.MyResult
import com.goorm.kkiri.domain.repository.BoardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BoardRepositoryImpl @Inject constructor(
    private val dataSource: BoardDataSource
): BoardRepository {

    override suspend fun getBoardByPage(
        type: String,
        page: Int
    ): Flow<BaseResponse<BoardPageDto>> = dataSource.getBoardByPage(type, page)

    override suspend fun getBoardInfo(
        boardId: Long
    ): Flow<BaseResponse<BoardDetailDto>> = dataSource.getBoardDetail(boardId)

    override suspend fun getHomeBard(
        type: String,
        page: Int
    ): Flow<BaseResponse<List<BoardHomeDto>>> = dataSource.getHomeBoard(type, page)


    override suspend fun getMyWrittenBoard(
        userId: Long,
        type: String,
        page: Int
    ): Flow<BaseResponse<MyResult>> = dataSource.getMyWrittenBoard(userId, type, page)


}