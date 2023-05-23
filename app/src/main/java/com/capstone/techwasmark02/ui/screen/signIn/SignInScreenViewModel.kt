package com.capstone.techwasmark02.ui.screen.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.mappers.toUserSession
import com.capstone.techwasmark02.data.model.UserLoginInfo
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.UserLoginResponse
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val userApiRepository: TechwasUserApiRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private val _userToSignInState: MutableStateFlow<UiState<UserLoginResponse>?> = MutableStateFlow(null)
    val userToSignInState = _userToSignInState.asStateFlow()

    private val _userToSignInInfo: MutableStateFlow<UserLoginInfo> =
        MutableStateFlow(UserLoginInfo(
            email = "",
            password = ""
        ))
    val userToSignInInfo = _userToSignInInfo.asStateFlow()

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = preferencesRepository.getActiveSession()
            when(result) {
                is Resource.Error -> {
                    _userSessionState.value = null
                }
                is Resource.Success -> {
                    _userSessionState.value = result.data
                }
            }
        }
    }

    fun signInUser() {
        _userToSignInState.value = UiState.Loading()
        viewModelScope.launch {
            val result = userApiRepository.userLogin(_userToSignInInfo.value)
            when(result) {
                is UiState.Success -> {
                    _userToSignInState.value = result
                    result.data?.loginResult?.toUserSession()
                        ?.let { preferencesRepository.saveSession(it) }
                }
                is UiState.Error -> {
                    _userToSignInState.value = result
                }
                else -> {
                // do nothing
                }
            }
        }
    }

    fun updateUserSignInInfo(userToSignInInfo: UserLoginInfo) {
        _userToSignInInfo.value = userToSignInInfo
    }
}