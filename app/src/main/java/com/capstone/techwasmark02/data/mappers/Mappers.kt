package com.capstone.techwasmark02.data.mappers

import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity
import com.capstone.techwasmark02.data.model.FavoriteArticle
import com.capstone.techwasmark02.data.model.UserSession
import com.capstone.techwasmark02.data.remote.response.LoginResult

fun LoginResult.toUserSession(): UserSession {
    return UserSession(
        userLoginToken = token,
        userNameId = userId
    )
}

fun FavoriteArticleEntity.toFavoriteArticle(): FavoriteArticle {
    return FavoriteArticle(
        id = id,
        name = name,
        desc = desc,
        imageURL = articleImageURL,
        compId = componentId
    )
}

fun FavoriteArticle.toFavoriteArticleEntity(): FavoriteArticleEntity {
    return FavoriteArticleEntity(
        id = id,
        name = name,
        componentId = compId,
        articleImageURL = imageURL,
        desc = desc
    )
}
