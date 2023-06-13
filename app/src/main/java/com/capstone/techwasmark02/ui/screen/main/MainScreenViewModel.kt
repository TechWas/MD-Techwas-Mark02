package com.capstone.techwasmark02.ui.screen.main

import androidx.lifecycle.ViewModel
import com.capstone.techwasmark02.ui.componentType.BottomBarItemType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel: ViewModel() {

    private val _selectedBottomBarType: MutableStateFlow<BottomBarItemType> = MutableStateFlow(
        BottomBarItemType.Home
    )
    val selectedBottomBarType = _selectedBottomBarType.asStateFlow()

    fun updateSelectedBottomBartype(newItemType: BottomBarItemType) {
        _selectedBottomBarType.value = newItemType
    }

}