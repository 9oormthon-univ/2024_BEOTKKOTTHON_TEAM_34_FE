package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.HomeUserInfoDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MemberService {

    // Response 추가 해야 함
    @POST("/user/signup")
    suspend fun signUp() {
    }

    @GET("/api/user/login")
    suspend fun login(
        @Query("username") userName: String,
        @Query("password") password: String
    ): BaseResponse<Long>

    @GET("/api/user/loginInfo")
    suspend fun getHomeUserInfo(
        @Query("userId") userId: Long
    ): BaseResponse<HomeUserInfoDto>



    // ...
}