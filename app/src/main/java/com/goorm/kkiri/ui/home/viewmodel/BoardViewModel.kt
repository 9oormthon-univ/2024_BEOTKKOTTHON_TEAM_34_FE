package com.goorm.kkiri.ui.home.viewmodel

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.KkiriApplication
import com.goorm.kkiri.R
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardHomeDto
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.repository.BoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardRepository: BoardRepository
) : ViewModel() {

    private var _boardList = MutableStateFlow(BoardPageDto())
    val boardList: StateFlow<BoardPageDto> = _boardList
    private var _homeBoardHelpMe = MutableStateFlow(BaseResponse<List<BoardHomeDto>>())
    val homeBoardHelpMe: StateFlow<BaseResponse<List<BoardHomeDto>>> = _homeBoardHelpMe
    private var _homeBoardHelpYou = MutableStateFlow(BaseResponse<List<BoardHomeDto>>())
    val homeBoardHelpYou: StateFlow<BaseResponse<List<BoardHomeDto>>> = _homeBoardHelpYou

    fun getBoardByPage(type: String, page: Int) {
        viewModelScope.launch {
            try {
                boardRepository.getBoardByPage(type, page).collect {
                    if (it.result != null) {
                        _boardList.value = it.result
                    }
                }
            } catch (e: Exception) {
                Log.e("Get Board By Page Error", e.message.toString())
            }
        }
    }

    fun getHomeBoardHelpMe(type: String, page: Int) {
        viewModelScope.launch {
            try {
                boardRepository.getHomeBard(type, page).collect {
                    _homeBoardHelpMe.value = it
                }
            } catch (e: Exception) {
                Log.e("Get Home Board Help Me Error", e.message.toString())
            }
        }
    }

    fun getHomeBoardHelpYou(type: String, page: Int) {
        viewModelScope.launch {
            try {
                boardRepository.getHomeBard(type, page).collect {
                    _homeBoardHelpYou.value = it
                }
            } catch (e: Exception) {
                Log.e("Get Home Board Help Me Error", e.message.toString())
            }
        }
    }

    fun changeHelpMeBackgroundTint(isHelpMe: Boolean): Int {
        return if (isHelpMe) {
            ContextCompat.getColor(KkiriApplication.application, R.color.blue_main)
        } else ContextCompat.getColor(KkiriApplication.application, R.color.gray_20)
    }

    fun changeHelpYouBackgroundTint(isHelpYou: Boolean): Int {
        return if (isHelpYou) {
            ContextCompat.getColor(KkiriApplication.application, R.color.blue_main)
        } else ContextCompat.getColor(KkiriApplication.application, R.color.gray_20)
    }
}