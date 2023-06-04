package com.capstone.techwasmark02.ui.screen.catalogSingleComponent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.apiService.TechwasComponentApi
import com.capstone.techwasmark02.data.remote.response.Component
import com.capstone.techwasmark02.data.remote.response.ComponentResponse
import com.capstone.techwasmark02.repository.TechwasComponentApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogSingleComponentScreenViewModel @Inject constructor(
    private val componentApiRepository: TechwasComponentApiRepository
) : ViewModel() {

    private val _componentState: MutableStateFlow<UiState<ComponentResponse>> = MutableStateFlow(UiState.Loading())
    val componentState = _componentState.asStateFlow()



    suspend fun updateComponentState(componentId: Int) {
        viewModelScope.launch {
            _componentState.value = componentApiRepository.fetchComponentById(componentId = componentId)
        }
    }

}