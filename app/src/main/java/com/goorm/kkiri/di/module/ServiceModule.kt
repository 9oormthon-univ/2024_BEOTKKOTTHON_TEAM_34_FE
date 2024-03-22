package com.goorm.kkiri.di.module

import com.goorm.kkiri.data.remote.MemberService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideMemberService(
        @NetworkModule.KkiriInterceptorOkHttpClient retrofit: Retrofit
    ): MemberService = retrofit.create(MemberService::class.java)
}