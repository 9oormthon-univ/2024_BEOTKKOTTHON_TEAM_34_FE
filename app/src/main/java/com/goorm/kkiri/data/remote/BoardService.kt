package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.model.response.MyResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardService {

    @GET("/board/{type}")
    suspend fun getBoardByPage(
        @Path("type") type: String,
        @Query("pageable") pageable: Pageable
    ): BaseResponse<BoardPageDto>

    @GET("api/board/user/{userId}")
    suspend fun getMyWrittenByPage(
        @Path("userId") userId: Long,
        @Query("type") type: String,
        @Query("page") page: Int
    ): BaseResponse<MyResult>
}