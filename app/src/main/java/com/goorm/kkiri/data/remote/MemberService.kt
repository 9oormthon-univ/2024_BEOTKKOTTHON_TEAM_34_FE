package com.goorm.kkiri.data.remote

import retrofit2.http.POST

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