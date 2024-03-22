package com.goorm.kkiri.domain.model.response

data class ChatMain(
    val chatId : Long,
    val receiver : String?,
    val expReceiver : String?,
    val dateTime : String,
    val confRead : Boolean
)
