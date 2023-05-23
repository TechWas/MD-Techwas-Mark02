package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.model.UserLoginInfo
import com.capstone.techwasmark02.data.model.UserRegisterInfo
import com.capstone.techwasmark02.data.remote.apiService.TechwasUserApi
import com.capstone.techwasmark02.data.remote.response.UserLoginResponse
import com.capstone.techwasmark02.ui.common.UiState
import java.lang.Exception
import javax.inject.Inject

class TechwasUserApiRepository @Inject constructor(
    private val userApi: TechwasUserApi
) {

    suspend fun userLogin(userLoginInfo: UserLoginInfo) : UiState<UserLoginResponse> {

        val response = try {
            userApi.login(userLoginInfo)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to authenticate user")
        }
        return UiState.Success(data = response)
    }

    suspend fun userRegister(userRegisterInfo: UserRegisterInfo) : UiState<String> {

        val response = try {
            userApi.register(userRegisterInfo)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to create user")
        }
        return UiState.Success(data = response)
    }

}