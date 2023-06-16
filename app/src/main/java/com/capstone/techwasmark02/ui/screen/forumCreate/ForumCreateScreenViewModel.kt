package com.capstone.techwasmark02.ui.screen.forumCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.ForumToCreateInfo
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.CreateForumResponse
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasForumApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForumCreateScreenViewModel @Inject constructor(
    private val forumApiRepository: TechwasForumApiRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    private val _createForumState: MutableStateFlow<UiState<CreateForumResponse>?> = MutableStateFlow(null)
    val createForumState = _createForumState.asStateFlow()

    private val _forumToCreateInfo: MutableStateFlow<ForumToCreateInfo> = MutableStateFlow(
        ForumToCreateInfo(
            category = "Mouse",
            content = "",
            imageUrl = "",
            location = "",
            title = ""
        )
    )
    val forumToCreateInfo = _forumToCreateInfo.asStateFlow()

    fun updateForumToCreateInfo(forumToCreateInfo: ForumToCreateInfo) {
        _forumToCreateInfo.value = forumToCreateInfo
    }

    fun createNewForum() {
        _createForumState.value = UiState.Loading()
        viewModelScope.launch {
            _createForumState.value = _userSessionState.value?.userLoginToken?.accessToken?.let {
                forumApiRepository.createNewForum(
                    forumToCreateInfo = _forumToCreateInfo.value,
                    userToken = it
                )
            }
        }
    }

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

}