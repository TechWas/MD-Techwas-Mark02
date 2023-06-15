package com.capstone.techwasmark02.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = preferencesRepository.getActiveSession()
            when(result) {
                is Resource.Error -> {
                    _userSessionState.value = UserSession(
                        userLoginToken = Token(accessToken = ""),
                        userNameId = UserId(username = "", id = 0)
                    )
                }
                is Resource.Success -> {
                    _userSessionState.value = result.data
                }
            }
        }
    }

    fun clearUserSession() {
        viewModelScope.launch {
            val result = preferencesRepository.clearSession()
            when(result) {
                is Resource.Error -> {
                    // do nothing
                }
                is Resource.Success -> {
                    _userSessionState.value = UserSession(
                        userLoginToken = Token(accessToken = ""),
                        userNameId = UserId(username = "-", id = 0)
                    )
                }
            }
        }
    }

}