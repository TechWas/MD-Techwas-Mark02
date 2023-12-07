package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.remote.response.ComponentResponse
import com.capstone.techwasmark02.data.remote.response.ComponentsResponse
import com.capstone.techwasmark02.data.remote.response.UsableComponentsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TechwasComponentApi {

    @GET("components/")
    suspend fun fetchComponents(
        @Header("Authorization") token: String,
    ) : ComponentsResponse

    @GET("components/{id}")
    suspend fun fetchComponentById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : ComponentResponse

    @GET("smallparts/bycompid/{compid}")
    suspend fun fetchUsableComponents(
        @Path("compid") compid: Int
    ) : UsableComponentsResponse

    companion object {
        const val BASE_URL = "https://backend-api-t7biziab7a-et.a.run.app//"
    }
}