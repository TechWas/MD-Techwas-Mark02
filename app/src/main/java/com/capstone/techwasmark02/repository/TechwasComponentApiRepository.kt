package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.remote.apiService.TechwasComponentApi
import com.capstone.techwasmark02.data.remote.response.ComponentResponse
import com.capstone.techwasmark02.data.remote.response.ComponentsResponse
import com.capstone.techwasmark02.data.remote.response.UsableComponentsResponse
import com.capstone.techwasmark02.ui.common.UiState
import javax.inject.Inject

class TechwasComponentApiRepository @Inject constructor(
    private val componentApi: TechwasComponentApi
) {

    suspend fun fetchComponents(userToken: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySUQiOiJ1c2VyQGV4YW1wbGUuY29tIiwiZXhwaXJ5IjoxNjg1ODcyMDkwLjMwNjU4ODJ9.cvaEjnnWe4Z3Hl-ImAIKyguTWeuntb6vOuwGCa1rr2w") : UiState<ComponentsResponse> {

        val response = try {
            componentApi.fetchComponents(token = userToken)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to fetch components")
        }
        return UiState.Success(data = response)
    }

    suspend fun fetchComponentById(userToken: String = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySUQiOiJ1c2VyQGV4YW1wbGUuY29tIiwiZXhwaXJ5IjoxNjg1ODcyMDkwLjMwNjU4ODJ9.cvaEjnnWe4Z3Hl-ImAIKyguTWeuntb6vOuwGCa1rr2w", componentId: Int) : UiState<ComponentResponse> {

        val response = try {
            componentApi.fetchComponentById(token = userToken, id = componentId)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to fetch components")
        }
        return UiState.Success(data = response)
    }

    suspend fun fetchUsableComponents(compId: Int): UiState<UsableComponentsResponse> {
        val response = try {
            componentApi.fetchUsableComponents(compid = compId)
        } catch (e: Exception) {
            return UiState.Error(message = e.message ?: "Fail to fetch usable components")
        }
        return UiState.Success(data = response)
    }

}