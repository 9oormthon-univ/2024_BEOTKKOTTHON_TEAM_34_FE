package com.goorm.kkiri.ui.mypage.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.data.local.DataSource
import com.goorm.kkiri.domain.model.response.MyPost
import com.goorm.kkiri.domain.model.response.MyResult
import com.goorm.kkiri.domain.repository.BoardRepository
import com.goorm.kkiri.domain.repository.MyWrittenRepository
import com.goorm.kkiri.ui.mypage.HelpListFragment
import com.goorm.kkiri.ui.mypage.HelpedListFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TabItem(val title: String, val fragment: Fragment)

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class ImWriteViewModel @Inject constructor(
    private val boardRepository: BoardRepository
): ViewModel() {
    // HelpListFragment에서 사용할 아이템 리스트
    private val _helpItems = MutableStateFlow(MyResult())
    val helpItems: StateFlow<MyResult> = _helpItems

    private val _helpedItems = MutableStateFlow(MyResult())
    val helpedItems: StateFlow<MyResult> = _helpedItems

    fun getMyWrittenByPage(userId: Long, type: String, page: Int) {
        viewModelScope.launch {
            try {
                boardRepository.getMyWrittenBoard(userId, type, page).collect {
                    _helpedItems.value = it.result
                    _helpItems.value = it.result
                }
            } catch (e: Exception) {
                Log.e("Get Board By Page Error", e.message.toString())
            }
        }
    }

    //탭 아이템
    private val _tabItems = MutableLiveData<List<TabItem>>()
    val tabItems: LiveData<List<TabItem>> get() = _tabItems


    init {
        setupInitialData()
        updateTabItems()
    }


    //초기 데이터 세팅
    private fun setupInitialData() {
        DataSource.initMyWrittenMenuItems()
        DataSource.initMyWrittenMenuItems2()
    }

    fun fetchHelpDate(pos : Long) = _helpItems.value.list[pos.toInt()]
    fun fetchHelpedDate(pos : Long) = _helpedItems.value.list[pos.toInt()]
    fun updateHelpList(pos: Int, item: MyPost) {
        val updatedList = _helpItems.value.list.toMutableList()
        updatedList.let {
            if (pos >= 0 && pos < it.size) {
                it[pos] = item
                _helpItems.value.list = it
            }
        }
    }
    fun updateHelpedList(pos: Int, item: MyPost) {
        val updatedList = _helpedItems.value.list.toMutableList()
        updatedList.let {
            if (pos >= 0 && pos < it.size) {
                it[pos] = item
                _helpedItems.value.list = it
            }
        }
    }


    //임시값 1개 넣어주는 함수
    //여기에 서버에서 온 값 파싱
    fun createFirstItem() {/*
        val ld = LocalDate.now()
        val items = MyPost(1, 2, ld, "기타레슨 받고싶어요!", null, "콩 드립니다.")

        val currentList = _helpItems.value.list.toMutableList()
        currentList.add(0, items)
        _helpItems.value.list = ArrayList(currentList)


        val currentList2 = _helpedItems.value.list.toMutableList()
        currentList2.add(0, items)
        _helpedItems.value.list = ArrayList(currentList2)

        Log.d("help add 사이즈","${(_helpItems.value as ArrayList<MyWrittenMenuItem>).size}")
        Log.d("helped add 사이즈","${(_helpedItems.value as ArrayList<MyWrittenMenuItem>).size}")*/
    }

    //1개 뺴주는 함수
    //제거하면 사라지는함수
    fun removeFirstItem() {
        val currentList = _helpItems.value.list.toMutableList()
        if (currentList.isNotEmpty()) {
            currentList.removeAt(0)
            _helpItems.value.list = ArrayList(currentList)

        }
        val currentList2 = _helpedItems.value.list.toMutableList()
        if (currentList2.isNotEmpty()) {
            currentList2.removeAt(0)
            _helpedItems.value.list = ArrayList(currentList2)

            Log.d("help rem사이즈","${(_helpItems.value as ArrayList<MyPost>).size}")
            Log.d("helped rem사이즈","${(_helpedItems.value as ArrayList<MyPost>).size}")
        }
    }

    private fun updateTabItems() {
        val newTabItems = listOf(TabItem("도와주세요!!", HelpListFragment()), TabItem("도와줄게요!!", HelpedListFragment()))
        _tabItems.value = newTabItems
    }
}
