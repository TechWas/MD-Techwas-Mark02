package com.capstone.techwasmark02.repository

import com.capstone.techwasmark02.data.local.database.FavoriteArticleDatabase
import com.capstone.techwasmark02.data.mappers.toFavoriteArticleEntity
import com.capstone.techwasmark02.data.model.FavoriteArticle
import javax.inject.Inject

class FavoriteArticleRepository @Inject constructor(private val favArticleDatabase: FavoriteArticleDatabase) {

    fun getFavArticles() = favArticleDatabase.favoriteArticleDao.getFavoriteArticles()

    fun getFavArticleById(id: Int) = favArticleDatabase.favoriteArticleDao.getFavoriteArticleById(id = id)

    suspend fun upsertFavoriteArticle(favoriteArticle: FavoriteArticle) = favArticleDatabase.favoriteArticleDao.upsertFavoriteArticle(favoriteArticle.toFavoriteArticleEntity())

    suspend fun deleteFavoriteArticle(
        favoriteArticle: FavoriteArticle
    ) = favArticleDatabase.favoriteArticleDao.deleteFavoriteArticle(favoriteArticle.toFavoriteArticleEntity())

}