package com.capstone.techwasmark02.ui.screen.forum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.techwasmark02.data.remote.response.ForumResponse
import com.capstone.techwasmark02.repository.TechwasForumApiRepository
import com.capstone.techwasmark02.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForumScreenViewModel @Inject constructor(
    private val forumApiRepository: TechwasForumApiRepository
): ViewModel() {

    private val _forumList: MutableStateFlow<UiState<ForumResponse>?> = MutableStateFlow(null)
    val forumList = _forumList.asStateFlow()

    private val _searchBoxValue: MutableStateFlow<String> = MutableStateFlow("")
    val searchBoxValue = _searchBoxValue.asStateFlow()

    fun updateSearchBoxValue(newValue: String) {
        _searchBoxValue.value = newValue
    }

    init {
        _forumList.value = UiState.Loading()
        viewModelScope.launch {
            _forumList.value = forumApiRepository.fetchAllForum()
        }
    }

}