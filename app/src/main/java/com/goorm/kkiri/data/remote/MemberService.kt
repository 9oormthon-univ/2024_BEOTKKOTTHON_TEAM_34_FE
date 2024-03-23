package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.HomeUserInfoDto
import com.goorm.kkiri.domain.model.response.MyPageDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MemberService {

    @GET("/api/user/login")
    suspend fun login(
        @Query("username") userName: String,
        @Query("password") password: String
    ): BaseResponse<Long>

    @GET("/api/user/loginInfo")
    suspend fun getHomeUserInfo(
        @Query("userId") userId: Long
    ): BaseResponse<HomeUserInfoDto>

    @GET("/api/user/{userId}/mypage")
    suspend fun getMyPageInfo(
        @Path("userId") userId: Long
    ): BaseResponse<MyPageDto>
}