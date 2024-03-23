package com.goorm.kkiri.data.source.board

import android.util.Log
import com.goorm.kkiri.data.remote.BoardService
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.model.response.MyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BoardDataSource @Inject constructor(
    private val boardService: BoardService
) {
    fun getBoardByPage(type: String, pageable: Pageable): Flow<BaseResponse<BoardPageDto>> = flow {
        val result = boardService.getBoardByPage(type, pageable)
        emit(result)
    }.catch {
        Log.e("Get Board by Page Error", it.message.toString())
    }

    fun getMyWrittenBoard(userId: Long, type: String, page: Int): Flow<BaseResponse<MyResult>> = flow {
        val result = boardService.getMyWrittenByPage(userId, type, page)
        emit(result)
    }.catch {
        Log.e("Get My Written Board Failure", it.message.toString())
    }


}