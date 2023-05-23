package com.capstone.techwasmark02.data.remote.apiService

import com.capstone.techwasmark02.data.model.UserLoginInfo
import com.capstone.techwasmark02.data.model.UserRegisterInfo
import com.capstone.techwasmark02.data.remote.response.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TechwasUserApi {

    @POST("user/login")
    suspend fun login(
        @Body userLoginInfo: UserLoginInfo
    ) : UserLoginResponse

    @POST("user/signup")
    suspend fun register(
        @Body userRegisterInfo: UserRegisterInfo
    ) : String



    companion object {
        const val BASE_URL = "https://the-prophecy-fwd5gpydiq-uc.a.run.app/"
    }

}