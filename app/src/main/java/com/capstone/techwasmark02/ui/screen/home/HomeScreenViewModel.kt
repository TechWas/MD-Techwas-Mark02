package com.capstone.techwasmark02.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.repository.TechwasForumApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val articleApiRepository: TechwasArticleRepository,
    private val forumApiRepository: TechwasForumApiRepository
): ViewModel() {

    private val _latestArticleState: MutableStateFlow<UiState<ArticleResultResponse>?> = MutableStateFlow(null)
    val latestArticleState = _latestArticleState.asStateFlow()

    private val _forumList: MutableStateFlow<UiState<ForumResponse>?> = MutableStateFlow(null)
    val forumList = _forumList.asStateFlow()

    init {
        _latestArticleState.value = UiState.Loading()
        _forumList.value = UiState.Loading()

        viewModelScope.launch {
            _latestArticleState.value = articleApiRepository.getAllArticle()
            _forumList.value = forumApiRepository.fetchAllForum()
        }
    }

}