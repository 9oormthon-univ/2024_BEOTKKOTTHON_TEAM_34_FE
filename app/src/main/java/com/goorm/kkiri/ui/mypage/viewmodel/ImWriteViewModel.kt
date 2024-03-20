package com.goorm.kkiri.ui.mypage.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.ui.mypage.EmptyListFragment
import com.goorm.kkiri.ui.mypage.HelpListFragment
import com.goorm.kkiri.ui.mypage.HelpedListFragment
import java.time.LocalDate

data class TabItem(val title: String, val fragment: Fragment)

@RequiresApi(Build.VERSION_CODES.O)
class ImWriteViewModel : ViewModel() {

    //아이템 리스트 (리사이클러뷰)
    private val _itemList = MutableLiveData<ArrayList<MyWrittenMenuItem>>()
    val itemList: LiveData<ArrayList<MyWrittenMenuItem>> get() = _itemList

    //탭 아이템
    private val _tabItems = MutableLiveData<List<TabItem>>()
    val tabItems: LiveData<List<TabItem>> get() = _tabItems

    //스크롤 이벤트
    private val _scrollToTopEvent = MutableLiveData<Unit>()
    val scrollToTopEvent: LiveData<Unit> get() = _scrollToTopEvent

    //플로팅 버튼 (탑 버튼) 이벤트
    private val _floatButtonVisibility = MutableLiveData<Boolean>()
    val floatButtonVisibility: LiveData<Boolean> get() = _floatButtonVisibility


    init {
        setupInitialData()
    }


    //초기 데이터 세팅
    private fun setupInitialData() {
        DataSource.initMyWrittenMenuItems()
        val recyclerViewItems = ArrayList(DataSource.writtenItems)
        _itemList.value = recyclerViewItems
        _floatButtonVisibility.value = false
        updateTabItems()
    }

    //임시값 1개 넣어주는 함수
    fun createFirstItem() {
        val ld = LocalDate.now()
        val items = MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다.")
        val currentList = _itemList.value.orEmpty().toMutableList()
        currentList.add(0, items)
        _itemList.value = ArrayList(currentList)
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

    //스크롤 이벤트 매개함수
    fun scrollToTop() {
        _scrollToTopEvent.value = Unit
    }

    //버튼 가시성 세팅 매개함수
    fun setFloatButtonVisibility(visible: Boolean) {
        _floatButtonVisibility.value = visible
    }
}
