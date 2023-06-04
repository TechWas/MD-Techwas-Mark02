package com.capstone.techwasmark02.ui.screen.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.ComponentsResponse
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasComponentApiRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val componentApiRepository: TechwasComponentApiRepository
): ViewModel() {

    private val _componentsState: MutableStateFlow<UiState<ComponentsResponse>?> = MutableStateFlow(null)
    val componentState = _componentsState.asStateFlow()

    init {
        viewModelScope.launch {
            _componentsState.value = componentApiRepository.fetchComponents()
        }
    }

}