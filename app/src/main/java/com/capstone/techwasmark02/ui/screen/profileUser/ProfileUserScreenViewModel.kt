package com.capstone.techwasmark02.ui.screen.profileUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId
import com.capstone.techwasmark02.repository.FavoriteArticleRepository
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUserScreenViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val articleApiRepository: TechwasArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository
): ViewModel() {

    private val _userSessionState: MutableStateFlow<UserSession?> = MutableStateFlow(null)
    val userSessionState = _userSessionState.asStateFlow()

    private val _bookmarkedArticleState: MutableStateFlow<UiState<ArticleResultResponse>?> = MutableStateFlow(null)
    val bookmarkedArticleState = _bookmarkedArticleState.asStateFlow()

    val favoriteArticlesFlow = favoriteArticleRepository.getFavArticles()

    init {
        _bookmarkedArticleState.value = UiState.Loading()

        viewModelScope.launch {
            _bookmarkedArticleState.value = articleApiRepository.getAllArticle()

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