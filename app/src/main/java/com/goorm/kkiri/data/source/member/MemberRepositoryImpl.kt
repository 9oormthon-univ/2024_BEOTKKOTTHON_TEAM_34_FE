package com.goorm.kkiri.data.source.member

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val dataSource: MemberDataSource
) : MemberRepository {

    override suspend fun login(userName: String, password: String): Flow<BaseResponse<String>> =
        dataSource.login(userName, password)

}