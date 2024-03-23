package com.goorm.kkiri.data.remote

import com.goorm.kkiri.domain.model.response.Message
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberService {

    // Response 추가 해야 함
    @POST("/user/signup")
    suspend fun signUp() {
    }

    @POST("/user/login")
    suspend fun login() {

    }



    // ...
}