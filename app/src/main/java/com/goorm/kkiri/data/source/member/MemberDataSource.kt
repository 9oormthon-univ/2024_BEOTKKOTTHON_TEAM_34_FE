package com.goorm.kkiri.data.source.member

import android.util.Log
import com.goorm.kkiri.data.remote.MemberService
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.HomeUserInfoDto
import com.goorm.kkiri.domain.model.response.MyPageDto
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

    suspend fun login(userName: String, password: String): Flow<BaseResponse<Long>> = flow {
        val result = memberService.login(userName, password)
        emit(result)
    }.catch {
        Log.e("Login Failure", it.message.toString())
    }

    fun getHomeUserInfo(userId: Long): Flow<BaseResponse<HomeUserInfoDto>> = flow {
        val result = memberService.getHomeUserInfo(userId)
        emit(result)
    }.catch {
        Log.e("Get Home User Info Failure", it.message.toString())
    }

    fun getMyPageInfo(userId: Long): Flow<BaseResponse<MyPageDto>> = flow {
        val result = memberService.getMyPageInfo(userId)
        emit(result)
    }.catch {
        Log.e("Get MyPage Info Failure", it.message.toString())
    }
}