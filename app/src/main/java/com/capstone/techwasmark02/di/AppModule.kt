package com.capstone.techwasmark02.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.capstone.techwasmark02.data.local.database.FavoriteArticleDatabase
import com.capstone.techwasmark02.data.remote.apiService.TechwasArticleApi
import com.capstone.techwasmark02.data.remote.apiService.TechwasComponentApi
import com.capstone.techwasmark02.data.remote.apiService.TechwasPredictionApi
import com.capstone.techwasmark02.data.remote.apiService.TechwasUserApi
import com.capstone.techwasmark02.repository.FavoriteArticleRepository
import com.capstone.techwasmark02.repository.TechwasArticleRepository
import com.capstone.techwasmark02.repository.TechwasComponentApiRepository
import com.capstone.techwasmark02.repository.TechwasUserApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTechwasUserApi(): TechwasUserApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TechwasUserApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(TechwasUserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTechwasPredictionApi(): TechwasPredictionApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TechwasPredictionApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(TechwasPredictionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTechwasArticleApi(): TechwasArticleApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TechwasArticleApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(TechwasArticleApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTechwasComponentApi(): TechwasComponentApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(TechwasArticleApi.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(TechwasComponentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ) : DataStore<Preferences> {
        return applicationContext.userDataStore
    }

    @Provides
    fun provideTechwasUserApiRepository(
        api: TechwasUserApi
    ) = TechwasUserApiRepository(api)

    @Provides
    fun provideTechwasArticleRepository(
        apiArticle: TechwasArticleApi
    ) = TechwasArticleRepository(apiArticle)

    @Provides
    fun provideTechwasComponentApiRepository(
        api: TechwasComponentApi
    ) = TechwasComponentApiRepository(api)

    @Provides
    @Singleton
    fun provideFavoriteArticleDatabase(@ApplicationContext context: Context): FavoriteArticleDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteArticleDatabase::class.java,
            "fav_article_database"
        ).build()
    }

    @Provides
    fun provideFavoriteArticleRepository(
        favoriteArticleDatabase: FavoriteArticleDatabase
    ) = FavoriteArticleRepository(favArticleDatabase = favoriteArticleDatabase)

}