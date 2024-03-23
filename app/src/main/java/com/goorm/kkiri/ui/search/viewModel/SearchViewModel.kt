package com.goorm.kkiri.ui.search.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.model.response.BoardPageDto
import com.goorm.kkiri.domain.repository.BoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bordRepository: BoardRepository
) : ViewModel() {

    private var _searchResult = MutableStateFlow(BaseResponse<BoardPageDto>())
    val searchResult: StateFlow<BaseResponse<BoardPageDto>> = _searchResult

    fun searchResult(title: String, description: String, page: Int) {
        viewModelScope.launch {
            try {
                bordRepository.searchBoard(title, description, page).collect {
                    _searchResult.value = it
                }
            } catch (e: Exception) {
                Log.e("Get Search Board Error", e.message.toString())
            }
        }
    }
}