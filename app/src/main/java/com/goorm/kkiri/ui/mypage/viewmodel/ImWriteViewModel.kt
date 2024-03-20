package com.goorm.kkiri.ui.mypage.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.domain.model.response.MyWrittenMenuItem
import com.goorm.kkiri.ui.mypage.HelpListFragment
import com.goorm.kkiri.ui.mypage.HelpedListFragment
import java.time.LocalDate

data class TabItem(val title: String, val fragment: Fragment)

@RequiresApi(Build.VERSION_CODES.O)
class ImWriteViewModel : ViewModel() {
    //탭 아이템
    private val _tabItems = MutableLiveData<List<TabItem>>()
    val tabItems: LiveData<List<TabItem>> get() = _tabItems

    // HelpListFragment에서 사용할 아이템 리스트
    private val _helpItems = MutableLiveData<List<MyWrittenMenuItem>>()
    val helpItems: LiveData<List<MyWrittenMenuItem>> = _helpItems

    // HelpedListFragment에서 사용할 아이템 리스트
    private val _helpedItems = MutableLiveData<List<MyWrittenMenuItem>>()
    val helpedItems: LiveData<List<MyWrittenMenuItem>> = _helpedItems



    init {
        setupInitialData()
        updateTabItems()
    }


    //초기 데이터 세팅
    private fun setupInitialData() {
        DataSource.initMyWrittenMenuItems()
        DataSource.initMyWrittenMenuItems2()
        _helpItems.value = DataSource.writtenItems // 예시 메서드, 실제 구현에 맞게 변경 필요
        _helpedItems.value = DataSource.writtenItems2 // 예시 메서드, 실제 구현에 맞게 변경 필요
    }

    //임시값 1개 넣어주는 함수
    fun createFirstItem() {
        val ld = LocalDate.now()
        val items = MyWrittenMenuItem(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다.")

        val currentList = _helpItems.value.orEmpty().toMutableList()
        currentList.add(0, items)
        _helpItems.value = ArrayList(currentList)


        val currentList2 = _helpedItems.value.orEmpty().toMutableList()
        currentList2.add(0, items)
        _helpedItems.value = ArrayList(currentList2)

        Log.d("help add 사이즈","${(_helpItems.value as ArrayList<MyWrittenMenuItem>).size}")
        Log.d("helped add 사이즈","${(_helpedItems.value as ArrayList<MyWrittenMenuItem>).size}")
    }

    //1개 뺴주는 함수
    fun removeFirstItem() {
        val currentList = _helpItems.value.orEmpty().toMutableList()
        if (currentList.isNotEmpty()) {
            currentList.removeAt(0)
            _helpItems.value = ArrayList(currentList)

        }
        val currentList2 = _helpedItems.value.orEmpty().toMutableList()
        if (currentList2.isNotEmpty()) {
            currentList2.removeAt(0)
            _helpedItems.value = ArrayList(currentList2)

            Log.d("help rem사이즈","${(_helpItems.value as ArrayList<MyWrittenMenuItem>).size}")
            Log.d("helped rem사이즈","${(_helpedItems.value as ArrayList<MyWrittenMenuItem>).size}")
        }
    }

    private fun updateTabItems() {
        val newTabItems = listOf(TabItem("도와주세요!!", HelpListFragment()), TabItem("도와줄게요!!", HelpedListFragment()))
        _tabItems.value = newTabItems
    }
}
