package com.capstone.techwasmark02.ui.screen.singleArticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity
import com.capstone.techwasmark02.data.mappers.toFavoriteArticleEntity
import com.capstone.techwasmark02.data.model.FavoriteArticle
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.SingleArticleResponse
import com.capstone.techwasmark02.repository.FavoriteArticleRepository
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleArticleScreenViewModel @Inject constructor(
    private val articleRepository: TechwasArticleRepository,
    private val favoriteArticleRepository: FavoriteArticleRepository
): ViewModel() {

    private val _articleResult: MutableStateFlow<UiState<SingleArticleResponse>?> = MutableStateFlow(null)
    val articleResult = _articleResult.asStateFlow()

    fun getArticleById(id: Int) {
        _articleResult.value = UiState.Loading()
        viewModelScope.launch {
            _articleResult.value = articleRepository.getArticleById(id)
        }
    }

    var isArticleFavorited: Flow<FavoriteArticleEntity> = favoriteArticleRepository.getFavArticleById(id = 0)

    fun getFavArticleById(id: Int) {
        isArticleFavorited = favoriteArticleRepository.getFavArticleById(id = id)
    }

    fun updateArticleFavorited(
        articleGetFavorited: Boolean,
        favoriteArticle: FavoriteArticle
    ) {
        if (articleGetFavorited) {
            viewModelScope.launch {
                favoriteArticleRepository.upsertFavoriteArticle(favoriteArticle)
            }
        } else {
            viewModelScope.launch {
                favoriteArticleRepository.deleteFavoriteArticle(favoriteArticle)
            }
        }
    }
}