package com.goorm.kkiri.ui.chatting

import com.goorm.kkiri.domain.model.response.ChatRoomItem

interface ChatMessageSender {
    fun sendMessage(state: Boolean, chatRoomItem: ChatRoomItem)
}