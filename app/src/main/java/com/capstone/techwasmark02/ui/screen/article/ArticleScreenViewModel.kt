package com.capstone.techwasmark02.ui.screen.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.mappers.toUserSession
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.ui.common.UiState
import com.capstone.techwasmark02.ui.componentType.ArticleFilterType
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

    fun getAllArticle() {
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

    fun getAllFilterArticle(id: Int) {
        _articleList.value = UiState.Loading()
        viewModelScope.launch {
            val result = articleRepository.getAllArticle()
            when(result) {
                is UiState.Success -> {
                    if(id == 0) {
                        _articleList.value = result
                    } else {
                        val filteredArticles = result.data?.componentList?.filter {
                            it?.componentId == id
                        }
                        val filteredResult = ArticleResultResponse(
                            componentList = filteredArticles.orEmpty(),
                            error = result.message,
                            message = result.message
                        )
                        _articleList.value = UiState.Success(filteredResult)
                    }
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

//    fun getArticleByName(name: String) {
//        _articleList.value = UiState.Loading()
//        viewModelScope.launch {
//            val result = articleRepository.getArticleByName(name)
//            when(result) {
//                is UiState.Success -> {
//                    _articleList.value = result
//                }
//                is UiState.Error -> {
//                    _articleList.value = result
//                }
//                else -> {
//                    // do nothing
//                }
//            }
//        }
//    }

    fun getArticleByName(name: String, filterType: ArticleFilterType) {
        _articleList.value = UiState.Loading()
        viewModelScope.launch {
            if (name.isEmpty()) { // input kosong fetch article by filter
                getAllFilterArticle(filterType.id)
            } else {
                val result = articleRepository.getAllArticle()
                when(result) {
                    is UiState.Success -> {
                        val filteredArticles = if (filterType.id == 0) { // general
                            result.data?.componentList?.filter {
                                it?.name?.contains(name, ignoreCase = true) == true
                            }
                        } else {
                            result.data?.componentList?.filter {
                                it?.name?.contains(name, ignoreCase = true) == true && it.componentId == filterType.id
                            }
                        }
                        val filteredResult = ArticleResultResponse(
                            componentList = filteredArticles.orEmpty(),
                            error = result.message,
                            message = result.message
                        )
                        _articleList.value = UiState.Success(filteredResult)
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
}