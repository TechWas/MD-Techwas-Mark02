package com.capstone.techwasmark02.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.techwasmark02.data.local.database.dao.FavoriteArticleEntityDao
import com.capstone.techwasmark02.data.local.database.entity.FavoriteArticleEntity

@Database(
    entities = [FavoriteArticleEntity::class],
    version = 1
)
abstract class FavoriteArticleDatabase: RoomDatabase() {
    abstract val favoriteArticleDao: FavoriteArticleEntityDao
}