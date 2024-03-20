package com.goorm.kkiri.data.local

import android.os.Build
import androidx.annotation.RequiresApi
import com.goorm.kkiri.R
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.domain.model.response.PostItem
import java.time.LocalDate

object DataSource {

    var postItems = mutableListOf<PostItem>()
    var writtenItems = mutableListOf<MyWrittenMenuItem>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun initMyWrittenMenuItems(){
        writtenItems.clear()
        val ld = LocalDate.now()
        writtenItems.add(MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(1, 3, ld, "코딩 과제 도와주세요!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(1, 2, ld, "바퀴벌레 잡아주실 분!", null, "콩 드립니다."))
        writtenItems.add(MyWrittenMenuItem(1, 2, ld, "같이 게임해요", null, "콩 드립니다."))
    }
    fun initHelpMePostItems() {
        postItems.clear()
        postItems.add(
            PostItem(1, "코딩 알려주실분!", R.drawable.ic_app_logo, "5분전", 5, 6),
        )
        postItems.add(
            PostItem(2, "토익 800점 찍고 싶어요 토익 알려주실분 구합니다", R.drawable.ic_app_logo, "10분전", 5, 6),
        )
        postItems.add(
            PostItem(3, "편입 영어 도와주세요!", R.drawable.ic_app_logo, "45분전", 5, 6),
        )
        postItems.add(
            PostItem(4, "피그마 기초 사용", R.drawable.ic_app_logo, "1시간전", 5, 6),
        )
        postItems.add(
            PostItem(5, "안드로이드 개발 재능 나눔", R.drawable.ic_app_logo, "12시간전", 5, 6),
        )
        postItems.add(
            PostItem(6, "코딩 알려주실분!", R.drawable.ic_app_logo, "17시간전", 5, 6),
        )
        postItems.add(
            PostItem(7, "코딩 알려주실분!", R.drawable.ic_app_logo, "3월 18일", 5, 6),
        )
        postItems.add(
            PostItem(8, "코딩 알려주실분!", R.drawable.ic_app_logo, "3월 17일", 5, 6),
        )
        postItems.add(
            PostItem(9, "코딩 알려주실분!", R.drawable.ic_app_logo, "3월 16일", 5, 6),
        )
        postItems.add(
            PostItem(10, "코딩 알려주실분!", R.drawable.ic_app_logo, "3월 13일", 5, 6),
        )
    }
}