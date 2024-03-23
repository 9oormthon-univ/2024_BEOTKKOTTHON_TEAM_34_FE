package com.goorm.kkiri.data.local

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.domain.model.response.ChatMain
import com.goorm.kkiri.domain.model.response.ChatRoomItem
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.domain.model.response.HelpPostInfo
import com.goorm.kkiri.domain.model.response.MyImageItem
import com.goorm.kkiri.domain.model.response.BoardIndividualDto
import com.goorm.kkiri.domain.model.response.BoardVolunteerDto
import java.time.LocalDate
@RequiresApi(Build.VERSION_CODES.O)
object DataSource {
    val uri = Uri.parse("android.resource://com.goorm.kkiri/drawable/ic_camera")
    var writtenItems = mutableListOf<MyWrittenMenuItem>()
    var writtenItems2 = mutableListOf<MyWrittenMenuItem>()
    var pictureData = mutableListOf<MyImageItem>()
    var chatMainItems = mutableListOf<ChatMain>()
    var chatRoomItems = mutableListOf<ChatRoomItem>()
    val boardVolunteerDto = mutableListOf<BoardVolunteerDto>()

    fun initBoardVolunteerList() {
        boardVolunteerDto.add(
            BoardVolunteerDto(1, "유기견 자원봉사자 모집", R.mipmap.image_dog, 9, "~ 4/5")
        )
        boardVolunteerDto.add(
            BoardVolunteerDto(2, "농촌봉사활동 지원자 모집", R.mipmap.image_rural, 9, "~ 4/8")
        )
        boardVolunteerDto.add(
            BoardVolunteerDto(3, "구름대학교 장애도우미 모집", R.mipmap.image_student, 9, "~ 4/11")
        )
    }

    fun initChatMainMenuItems(){
        chatMainItems.clear()
        chatMainItems.add(ChatMain(1,"구름이","코딩 과제 도와주세요 ㅠㅠ","${LocalDate.now()}",true))
    }
    fun initChatRoomMenuItems(){
        chatRoomItems.clear()
    }

    fun initMyWrittenMenuItems(){
        writtenItems.clear()
        val ld = LocalDate.now()
        writtenItems.add(MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(2, 3, ld, "코딩 과제 도와주세요!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(3, 2, ld, "바퀴벌레 잡아주실 분!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(4, 2, ld, "같이 게임해요", null, "콩 드립니다."))
    }
    fun initMyWrittenMenuItems2(){
        writtenItems2.clear()
        val ld = LocalDate.now()
        writtenItems2.add(MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다."))
        writtenItems2.add(MyWrittenMenuItem(2, 2, ld, "바퀴벌레 잡아주실 분!", null, "콩 드립니다."))
        writtenItems2.add(MyWrittenMenuItem(3, 2, ld, "같이 게임해요", null, "콩 드립니다."))
    }
    fun initPicturesData(){
        pictureData.clear()
        pictureData.add(MyImageItem(0,uri,"0"))
    }

    fun getHelpPostInfo(): HelpPostInfo {
        return HelpPostInfo(
            1, "구르미", "코딩 알려주실분!",
            "이번에 컴퓨터 공학과에 새내기로 입학했습니다. 코딩을 1도 몰라서\n알려주실분 구해요ㅠㅠ\n언어는 파이썬 원합니다!",
            null, "1분 전", 10, 5
        )
    }
}