package com.goorm.kkiri.di.module

import com.goorm.kkiri.data.source.board.BoardDataSource
import com.goorm.kkiri.data.source.board.BoardRepositoryImpl
import com.goorm.kkiri.data.source.member.MemberDataSource
import com.goorm.kkiri.data.source.member.MemberRepositoryImpl
import com.goorm.kkiri.domain.repository.BoardRepository
import com.goorm.kkiri.domain.repository.MemberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMemberRepository(memberDataSource: MemberDataSource): MemberRepository =
        MemberRepositoryImpl(memberDataSource)

    @Singleton
    @Provides
    fun provideBoardRepository(boardDataSource: BoardDataSource): BoardRepository =
        BoardRepositoryImpl(boardDataSource)
}