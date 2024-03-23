package com.goorm.kkiri.ui.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardDetailDto
import com.goorm.kkiri.domain.repository.BoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val boardRepository: BoardRepository
) : ViewModel() {

    private var _boardDetail = MutableStateFlow(BaseResponse<BoardDetailDto>())
    val boardDetail: StateFlow<BaseResponse<BoardDetailDto>> = _boardDetail

    fun getBoardDetail(boardId: Long) {
        viewModelScope.launch {
            try {
                boardRepository.getBoardInfo(boardId).collect {
                    if (it.result != null) {
                        _boardDetail.value = it
                    }
                }
            } catch (e: Exception) {
                Log.e("Get Board Detail Error", e.message.toString())
            }
        }
    }
}