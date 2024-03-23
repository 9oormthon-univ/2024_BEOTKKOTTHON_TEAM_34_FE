package com.goorm.kkiri.ui.account.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goorm.kkiri.domain.model.base.BaseResponse
import com.goorm.kkiri.domain.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MemberViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private var _loginResult = MutableStateFlow(BaseResponse<String>("0", "성공", "1"))
    val loginResult: StateFlow<BaseResponse<String>> = _loginResult

    fun login(userName: String, password: String) {
        viewModelScope.launch {
            try {
                memberRepository.login(userName, password).collect {
                    _loginResult.value = it
                }
            } catch (e: Exception) {
                Log.e("Login Error", e.message.toString())
            }
        }
    }
}