package com.capstone.techwasmark02.ui.screen.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.model.UserRegisterInfo
import com.capstone.techwasmark02.data.remote.response.UserRegisterResponse
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val userApiRepository: TechwasUserApiRepository,
): ViewModel() {

    private val _userToSignUpState: MutableStateFlow<UiState<UserRegisterResponse>?> =
        MutableStateFlow(null)
    val userToSignUpState = _userToSignUpState.asStateFlow()

    private val _userToSignUpInfo: MutableStateFlow<UserRegisterInfo> =
        MutableStateFlow(
            UserRegisterInfo(
                fullname = "",
                email = "",
                password = ""
            )
        )
    val userToSignUpInfo = _userToSignUpInfo.asStateFlow()

    fun signUpUser() {
        _userToSignUpState.value = UiState.Loading()
        viewModelScope.launch {
            _userToSignUpState.value = userApiRepository.userRegister(_userToSignUpInfo.value)
        }
    }

    fun updateUserSignUpInfo(userToSignUpInfo: UserRegisterInfo) {
        _userToSignUpInfo.value = userToSignUpInfo
    }
}