package com.capstone.techwasmark02.ui.screen.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.mappers.toUserSession
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleScreenViewModel @Inject constructor(
    private val articleRepository: TechwasArticleRepository
): ViewModel() {

    private val _articleList: MutableStateFlow<UiState<ArticleResultResponse>?> = MutableStateFlow(null)
    val articleList = _articleList.asStateFlow()

    init {
       viewModelScope.launch {
           getAllArticle()
       }
    }

    private fun getAllArticle() {
        _articleList.value = UiState.Loading()
        viewModelScope.launch {
            val result = articleRepository.getAllArticle()
            when(result) {
                is UiState.Success -> {
                    _articleList.value = result
                }
                is UiState.Error -> {
                    _articleList.value = result
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
}