package com.goorm.kkiri.data.source.chat

import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.MessageDto
import com.goorm.kkiri.domain.repository.ChattingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val dataSource: ChatDataSource
) : ChattingRepository {

    override suspend fun getChattingByRoomId(chatRoomId: String): Flow<BaseResponse<MessageDto>> =
        dataSource.getChattingByRoomId(chatRoomId)
}