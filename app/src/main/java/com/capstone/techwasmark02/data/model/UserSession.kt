package com.capstone.techwasmark02.data.model

import com.capstone.techwasmark02.data.remote.response.Token
import com.capstone.techwasmark02.data.remote.response.UserId

data class UserSession(
    val userLoginToken: Token,
    val userNameId: UserId
)
