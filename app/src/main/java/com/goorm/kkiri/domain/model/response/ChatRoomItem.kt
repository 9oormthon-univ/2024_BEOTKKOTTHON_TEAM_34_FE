package com.goorm.kkiri.domain.model.response

data class ChatRoomItem(
    val chatId : Long, // 번호
    val sendType : String?, //보내는 사람 or 받는 사람
    val receiver : String?, // 보내는사람 이름
    val expReceiver : String?,// 내용
    val dateTime : String, //시간
    val stateProduct : Boolean //on off
)
