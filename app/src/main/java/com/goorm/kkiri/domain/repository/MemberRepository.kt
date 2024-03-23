package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.HomeUserInfoDto
import com.goorm.kkiri.domain.model.response.MyPageDto
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    suspend fun login(userName: String, password: String): Flow<BaseResponse<Long>>
    suspend fun getHomeUserInfo(userId: Long): Flow<BaseResponse<HomeUserInfoDto>>
    suspend fun getMyPageInfo(userId: Long): Flow<BaseResponse<MyPageDto>>
}