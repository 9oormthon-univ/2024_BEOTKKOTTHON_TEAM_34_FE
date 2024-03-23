package com.goorm.kkiri.domain.repository

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.MyResult
import kotlinx.coroutines.flow.Flow

interface MyWrittenRepository {
    suspend fun getMyWrittenByPage(userId: Int,type: String,page: Int): Flow<BaseResponse<MyResult>>

}