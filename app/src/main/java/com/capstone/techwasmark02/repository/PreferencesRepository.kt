package com.capstone.techwasmark02.repository

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.techwasmark02.R
import com.capstone.techwasmark02.common.Resource
import com.capstone.techwasmark02.data.model.UserSession
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    private val preferences: DataStore<Preferences>
) {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val adapter = moshi.adapter(UserSession::class.java)

    suspend fun saveSession(session: UserSession): Resource<UserSession> {
        try {
            preferences.edit { preferences ->
                preferences[KEY_SESSION] = adapter.toJson(session)
            }
        } catch (e: Exception) {
            return Resource.Error(message = "failed to save session, ${e.message}")
        }
        return Resource.Success(data = session, message = "Successfully saving user session")
    }

    suspend fun getActiveSession(): Resource<UserSession> {
        val session = try {
            preferences.data.map { preferences ->
                preferences[KEY_SESSION]
            }.first()
        } catch (e: Exception) {
            return Resource.Error(message = "failed to retrieve user session")
        }
        return Resource.Success(data = adapter.fromJson(session ?: EMPTY_JSON_STRING))
    }

    suspend fun clearSession(): Resource<String> {
        try {
            preferences.edit { preferences ->
                preferences.remove(KEY_SESSION)
            }
        } catch (e: Exception) {
            return Resource.Error(message = "failed to clear user session")
        }
        return Resource.Success(data = null, message = "success to clear user session")
    }


    private companion object {
        val KEY_SESSION = stringPreferencesKey(
            name = "session"
        )
        val EMPTY_JSON_STRING = "{\"userNameId\":{\"id\":0,\"username\":\"\"},\"userLoginToken\":{\"accessToken\":\"\"}}"
    }
}