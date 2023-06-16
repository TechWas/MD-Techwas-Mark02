package com.capstone.techwasmark02.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteArticleEntityDao {

    @Upsert
    suspend fun upsertFavoriteArticle(favoriteArticle: FavoriteArticleEntity)

    @Delete
    suspend fun deleteFavoriteArticle(
        favoriteArticle: FavoriteArticleEntity
    )

    @Query("SELECT * FROM fav_article_entity")
    fun getFavoriteArticles(): Flow<List<FavoriteArticleEntity>>

    @Query("SELECT * FROM fav_article_entity WHERE id = :id")
    fun getFavoriteArticleById(id: Int): Flow<FavoriteArticleEntity>

}