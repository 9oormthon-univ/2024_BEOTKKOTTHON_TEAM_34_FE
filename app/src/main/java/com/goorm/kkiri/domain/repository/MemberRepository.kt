package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    suspend fun login(userName: String, password: String): Flow<BaseResponse<String>>
}