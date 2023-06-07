package com.capstone.techwasmark02.ui.screen.catalogSingleComponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.apiService.TechwasComponentApi
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.Component
import com.capstone.techwasmark02.data.remote.response.ComponentResponse
import com.capstone.techwasmark02.data.remote.response.SmallPart
import com.capstone.techwasmark02.data.remote.response.UsableComponentsResponse
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.repository.TechwasComponentApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogSingleComponentScreenViewModel @Inject constructor(
    private val componentApiRepository: TechwasComponentApiRepository,
    private val articleRepository: TechwasArticleRepository
) : ViewModel() {

    private val _usableComponentsState: MutableStateFlow<UiState<UsableComponentsResponse>?> = MutableStateFlow(null)
    val usableComponentsState = _usableComponentsState.asStateFlow()

    private val _usableComponentList: MutableStateFlow<List<SmallPart>> = MutableStateFlow(emptyList())
    val usableComponentList = _usableComponentList.asStateFlow()

    private val _relatedArticleListState: MutableStateFlow<UiState<ArticleResultResponse>?> = MutableStateFlow(null)
    val relatedArticleListState = _relatedArticleListState.asStateFlow()

    fun updateUsableComponentsState(compId: Int) {
        _usableComponentsState.value = UiState.Loading()
        viewModelScope.launch {
            _usableComponentsState.value = componentApiRepository.fetchUsableComponents(compId)
        }
    }

    fun updateUsableComponentList(newList: List<SmallPart>) {
        _usableComponentList.value = newList
    }

    fun updateRelatedArticleListState(compId: Int) {
        _relatedArticleListState.value = UiState.Loading()
        viewModelScope.launch {
            _relatedArticleListState.value = articleRepository.getArticleByComponentId(id = compId)
        }
    }

}