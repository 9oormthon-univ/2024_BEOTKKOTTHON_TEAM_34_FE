package com.goorm.kkiri.data.source.board

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
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
        pageable: Pageable
    ): Flow<BaseResponse<BoardPageDto>> = dataSource.getBoardByPage(type, pageable)

    override suspend fun getMyWrittenBoard(
        userId: Long,
        type: String,
        page: Int
    ): Flow<BaseResponse<MyResult>> = dataSource.getMyWrittenBoard(userId, type, page)


}