package com.goorm.kkiri.data.source

import com.goorm.kkiri.data.remote.MemberService
import com.goorm.kkiri.domain.model.response.Message
import retrofit2.Call
import retrofit2.http.Path
import javax.inject.Inject

class MemberDataSource @Inject constructor(
    private val memberService: MemberService
) {

    suspend fun signUp() {
        // 구현
    }

    suspend fun login() {
        // 구현
    }

}