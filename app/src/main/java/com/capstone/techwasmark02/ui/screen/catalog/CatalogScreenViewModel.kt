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

    private val _searchBoxValue: MutableStateFlow<String> = MutableStateFlow("")
    val searchBoxValue = _searchBoxValue.asStateFlow()

    fun updateSearchBoxValue(newValue: String) {
        _searchBoxValue.value = newValue
    }

    init {
        _componentsState.value = UiState.Loading()

        viewModelScope.launch {
            _componentsState.value = componentApiRepository.fetchComponents()
        }
    }

}