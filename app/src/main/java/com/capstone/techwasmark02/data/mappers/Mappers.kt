package com.capstone.techwasmark02.data.mappers

import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.LoginResult

fun LoginResult.toUserSession(): UserSession {
    return UserSession(
        userLoginToken = token,
        userNameId = userId
    )
}