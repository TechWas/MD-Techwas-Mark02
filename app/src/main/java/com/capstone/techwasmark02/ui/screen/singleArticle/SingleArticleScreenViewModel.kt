package com.capstone.techwasmark02.ui.screen.singleArticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleArticleScreenViewModel @Inject constructor(
    private val articleRepository: TechwasArticleRepository
): ViewModel() {

    private val _articleResult: MutableStateFlow<UiState<SingleArticleResponse>?> = MutableStateFlow(null)
    val articleResult = _articleResult.asStateFlow()

    fun getArticleById(id: Int) {
        _articleResult.value = UiState.Loading()
        viewModelScope.launch {
            val result = articleRepository.getArticleById(id)
            when(result) {
                is UiState.Success -> {
                    _articleResult.value = result
                }
                is UiState.Error -> {
                    _articleResult.value = result
                }
                else -> {
                    // do nothing
                }
            }
        }
    }
}