package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.request.Pageable
import com.goorm.kkiri.domain.model.response.BoardPageDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardService {

    @GET("/api/board/{type}")
    suspend fun getBoardByPage(
        @Path("type") type: String,
        @Query("page") page: Int
    ): BaseResponse<BoardPageDto>
}