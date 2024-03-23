package com.goorm.kkiri.ui.home.viewmodel

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.KkiriApplication
import com.goorm.kkiri.R
import com.goorm.kkiri.domain.model.request.Pageable
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

    fun getBoardByPage(type: String, pageable: Pageable) {
        viewModelScope.launch {
            try {
                boardRepository.getBoardByPage(type, pageable).collect {
                    _boardList.value = it.result
                }
            } catch (e: Exception) {
                Log.e("Get Board By Page Error", e.message.toString())
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