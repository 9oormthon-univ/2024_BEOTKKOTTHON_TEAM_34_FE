package com.goorm.kkiri.ui.mypage.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.ui.mypage.EmptyListFragment
import com.goorm.kkiri.ui.mypage.HelpListFragment
import com.goorm.kkiri.ui.mypage.HelpedListFragment
import java.time.LocalDate

data class TabItem(val title: String, val fragment: Fragment)

@RequiresApi(Build.VERSION_CODES.O)
class ImWriteViewModel : ViewModel() {
    private val _itemList = MutableLiveData<ArrayList<MyWrittenMenuItem>>()
    val itemList: LiveData<ArrayList<MyWrittenMenuItem>> get() = _itemList
    private val _tabItems = MediatorLiveData<List<TabItem>>()
    val tabItems: LiveData<List<TabItem>> get() = _tabItems

    //임시 데이터 값 세팅
    init {
        val ld = LocalDate.now()
        val recyclerViewItems = ArrayList<MyWrittenMenuItem>().apply {
            add(MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다."))
            add(MyWrittenMenuItem(1, 3, ld, "코딩 과제 도와주세요!", null, "콩 드립니다."))
            add(MyWrittenMenuItem(1, 2, ld, "바퀴벌레 잡아주실 분!", null, "콩 드립니다."))
            add(MyWrittenMenuItem(1, 2, ld, "같이 게임해요", null, "콩 드립니다."))
        }

        //임시데이터로 초기 세팅
        _itemList.value = recyclerViewItems
        updateTabItems()

        //리스트 넣어주기
        _tabItems.addSource(_itemList) {
            updateTabItems()
        }
    }


    //임시값 1개 넣어주는 함수
    fun createFirstItem() {
        val ld = LocalDate.now()
        val items = MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다.")
        val currentList = _itemList.value.orEmpty().toMutableList() // 수정된 부분
        currentList.add(0, items)
        _itemList.value = ArrayList(currentList)
        // 탭 아이템을 업데이트
    }

    //1개 뺴주는 함수
    fun removeFirstItem() {
        val currentList = _itemList.value.orEmpty().toMutableList()
        if (currentList.isNotEmpty()) {
            currentList.removeAt(0)
            _itemList.value = ArrayList(currentList)
        }
    }

    //업데이트 해주는 함수 (아이템이 null이면 EmptyListFragment 리턴)
    private fun updateTabItems() {
        val items = _itemList.value
        val newTabItems = if (items.isNullOrEmpty()) {
            listOf(TabItem("도와주세요!!", EmptyListFragment()), TabItem("도와줄게요!!", EmptyListFragment()))
        } else {
            listOf(TabItem("도와주세요!!", HelpListFragment()), TabItem("도와줄게요!!", HelpedListFragment()))
        }
        _tabItems.value = newTabItems
    }


    fun setItemList(recyclerViewItems: ArrayList<MyWrittenMenuItem>) {
        _itemList.value = recyclerViewItems
    }

    fun getFirstItem(): MyWrittenMenuItem? {
        return itemList.value?.firstOrNull()
    }

}