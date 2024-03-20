package com.goorm.kkiri.data.local

import com.goorm.kkiri.R
import com.goorm.kkiri.domain.model.response.HelpPostInfo
import com.goorm.kkiri.domain.model.response.PostItem

object DataSource {

    var postItems = mutableListOf<PostItem>()


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

    fun getHelpPostInfo(): HelpPostInfo {
        return HelpPostInfo(
            1, "구르미", "코딩 알려주실분!",
            "이번에 컴퓨터 공학과에 새내기로 입학했습니다. 코딩을 1도 몰라서\n알려주실분 구해요ㅠㅠ\n언어는 파이썬 원합니다!",
            null, "1분 전", 10, 5
        )
    }
}