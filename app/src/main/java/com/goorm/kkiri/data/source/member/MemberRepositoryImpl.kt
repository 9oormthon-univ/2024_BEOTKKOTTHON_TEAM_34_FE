package com.goorm.kkiri.data.source.member

import com.goorm.kkiri.domain.repository.MemberRepository
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val dataSource: MemberDataSource
) : MemberRepository {

}