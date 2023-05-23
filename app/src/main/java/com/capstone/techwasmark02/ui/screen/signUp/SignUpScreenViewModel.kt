package com.capstone.techwasmark02.ui.screen.signUp

import androidx.lifecycle.ViewModel
import com.capstone.techwasmark02.repository.PreferencesRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import javax.inject.Inject

class SignUpScreenViewModel @Inject constructor(
    private val userApiRepository: TechwasUserApiRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

//    private val _userToSignUpState

}