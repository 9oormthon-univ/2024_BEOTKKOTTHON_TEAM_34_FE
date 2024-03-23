package com.goorm.kkiri.data.source.member

import com.goorm.kkiri.data.remote.MemberService
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