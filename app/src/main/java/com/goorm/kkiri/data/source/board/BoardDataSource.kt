package com.goorm.kkiri.data.source.board

import android.util.Log
import com.goorm.kkiri.data.remote.BoardService
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardDetailDto
import com.goorm.kkiri.domain.model.response.BoardPageDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BoardDataSource @Inject constructor(
    private val boardService: BoardService
) {
    fun getBoardByPage(type: String, page: Int): Flow<BaseResponse<BoardPageDto>> = flow {
        val result = boardService.getBoardByPage(type, page)
        emit(result)
    }.catch {
        Log.e("Get Board by Page Failure", it.message.toString())
    }

    fun getBoardDetail(boardId: Long): Flow<BaseResponse<BoardDetailDto>> = flow {
        val result = boardService.getBoardDetail(boardId)
        emit(result)
    }.catch {
        Log.e("Get Board Detail Failure", it.message.toString())
    }
}