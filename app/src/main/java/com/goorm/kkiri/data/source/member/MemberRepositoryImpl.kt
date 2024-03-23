package com.goorm.kkiri.data.source.member

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.HomeUserInfoDto
import com.goorm.kkiri.domain.model.response.MyPageDto
import com.goorm.kkiri.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val dataSource: MemberDataSource
) : MemberRepository {

    override suspend fun login(userName: String, password: String): Flow<BaseResponse<Long>> =
        dataSource.login(userName, password)

    override suspend fun getHomeUserInfo(userId: Long): Flow<BaseResponse<HomeUserInfoDto>> =
        dataSource.getHomeUserInfo(userId)

    override suspend fun getMyPageInfo(userId: Long): Flow<BaseResponse<MyPageDto>> =
        dataSource.getMyPageInfo(userId)
}