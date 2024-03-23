package com.goorm.kkiri.data.source.member

import android.util.Log
import com.goorm.kkiri.data.remote.MemberService
import com.goorm.kkiri.domain.model.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberDataSource @Inject constructor(
    private val memberService: MemberService
) {

    suspend fun signUp() {
        // 구현
    }

    suspend fun login(userName: String, password: String): Flow<BaseResponse<String>> = flow {
        val result = memberService.login(userName, password)
        emit(result)
    }.catch {
        Log.e("Login Failure", it.message.toString())
    }
}