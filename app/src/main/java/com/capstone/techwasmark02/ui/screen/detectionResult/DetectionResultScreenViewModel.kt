package com.capstone.techwasmark02.ui.screen.detectionResult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.ArticleResultResponse
import com.capstone.techwasmark02.data.remote.response.Prediction
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
class DetectionResultScreenViewModel @Inject constructor(
    private val componentApiRepository: TechwasComponentApiRepository,
    private val articleRepository: TechwasArticleRepository
) : ViewModel() {

    private val _usableComponentsListState: MutableStateFlow<List<UiState<UsableComponentsResponse>>?> = MutableStateFlow(null)
    val usableComponentsListState = _usableComponentsListState.asStateFlow()

    private val _selectedPrediction: MutableStateFlow<Int> = MutableStateFlow(0)
    val selectedPrediction = _selectedPrediction.asStateFlow()

    private val _currentlySelectedUsableComponentList: MutableStateFlow<List<SmallPart>> = MutableStateFlow(
        emptyList()
    )
    val currentlySelectedUsableComponentList = _currentlySelectedUsableComponentList.asStateFlow()

    private val _relatedArticleListState: MutableStateFlow<List<UiState<ArticleResultResponse>>?> = MutableStateFlow(null)
    val relatedArticleListState = _relatedArticleListState.asStateFlow()

    fun fetchAllUsableComponents(componentIdList: List<Prediction>) {
        viewModelScope.launch {
            val usableComponentsList = mutableListOf<UiState<UsableComponentsResponse>>()
            val articlesList = mutableListOf<UiState<ArticleResultResponse>>()
            componentIdList.forEach { prediction ->

                prediction.componentId.let {
                    val usableComponents = componentApiRepository.fetchUsableComponents(compId = it)
                    usableComponentsList.add(usableComponents)

                    val relatedArticleList = articleRepository.getArticleByComponentId(id = it)
                    articlesList.add(relatedArticleList)
                }
            }
            _usableComponentsListState.value = usableComponentsList
            _relatedArticleListState.value = articlesList
        }
    }

    fun updateSelectedPrediction(currentlySelectedPrediction: Int) {
        _selectedPrediction.value = currentlySelectedPrediction
    }

    fun updateCurrentlySelectedUsableComponentList(newList: List<SmallPart>) {
        _currentlySelectedUsableComponentList.value = newList
    }

//    fun updateRelatedArticleListState(compId: Int) {
//        _relatedArticleListState.value = UiState.Loading()
//        viewModelScope.launch {
//            _relatedArticleListState.value = articleRepository.getArticleByComponentId(id = compId)
//        }
//    }
}