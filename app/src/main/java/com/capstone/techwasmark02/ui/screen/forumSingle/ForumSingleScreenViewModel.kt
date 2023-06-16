package com.capstone.techwasmark02.ui.screen.forumSingle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.ForumCommentInfo
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.ForumCommentResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.data.remote.response.PostForumCommentResponse
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
class ForumSingleScreenViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val forumApiRepository: TechwasForumApiRepository
): ViewModel() {

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    private val _forumState: MutableStateFlow<UiState<ForumResponse>?> = MutableStateFlow(null)
    val forumState = _forumState.asStateFlow()

    private val _forumCommentState: MutableStateFlow<UiState<ForumCommentResponse>?> = MutableStateFlow(null)
    val forumCommentState = _forumCommentState.asStateFlow()

    private val _postingCommentState: MutableStateFlow<UiState<PostForumCommentResponse>?> = MutableStateFlow(null)
    val postingCommentState = _postingCommentState.asStateFlow()

    fun fetchForumById(id: Int) {
        _forumState.value = UiState.Loading()
        viewModelScope.launch {
            _forumState.value = _userSessionState.value?.userLoginToken?.accessToken?.let {
                forumApiRepository.fetchForumById(
                    id = id,
                    userToken = it
                )
            }
        }
    }

    fun fetchForumCommentByForumId(forumId: Int) {
        _forumCommentState.value = UiState.Loading()
        viewModelScope.launch {
            _forumCommentState.value = forumApiRepository.fetchForumCommentByForumId(forumId)
        }
    }

    fun postForumComment(comment: String, forumId: Int) {
        _postingCommentState.value = UiState.Loading()
        val forumCommentInfo = ForumCommentInfo(
            comment = comment,
            forumID = forumId
        )
        viewModelScope.launch {
            _userSessionState.value?.userLoginToken?.accessToken?.let {
                _postingCommentState.value = forumApiRepository.postForumComment(
                    forumCommentInfo = forumCommentInfo,
                    userToken = it
                )
            }
        }
    }

    fun clearPostingCommentState() {
        _postingCommentState.value = null
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