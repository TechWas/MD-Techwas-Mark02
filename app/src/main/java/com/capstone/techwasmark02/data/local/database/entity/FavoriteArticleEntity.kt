package com.capstone.techwasmark02.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "fav_article_entity" )
data class FavoriteArticleEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val desc: String,
    val articleImageURL: String,
    val componentId: Int
)
