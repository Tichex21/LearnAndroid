package com.sachinshah.practical.prefdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.sachinshah.practical.room.entity.UserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

object PrefKey {

    val USER_ID = stringPreferencesKey("user_id")
    val IS_LOGIN = booleanPreferencesKey("is_login")
    val USER_MODEL = stringPreferencesKey("user")

}

class PrefDatastoreUtils @Inject constructor(@ApplicationContext val context: Context) {

    val Context.datastore by preferencesDataStore(name = "App_Datastore")

    suspend fun setUserId(userId: String) {
        context.datastore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[PrefKey.USER_ID] = userId
            }
        }
    }

    fun userId(): Flow<String> = context.datastore.data.map { it[PrefKey.USER_ID] ?: "" }


    suspend fun setLogin(isLogin: Boolean) {
        context.datastore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[PrefKey.IS_LOGIN] = isLogin
            }
        }
    }

    fun isLogin(): Flow<Boolean> = context.datastore.data.map { it[PrefKey.IS_LOGIN] ?: false }


    suspend fun setUser(userModel: UserModel) {
        context.datastore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[PrefKey.USER_MODEL] = Gson().toJson(userModel)
            }
        }
    }

    fun user(): Flow<UserModel> = context.datastore.data.map {
        Gson().fromJson(
            it[PrefKey.USER_MODEL] ?: "",
            UserModel::class.java
        ) ?: UserModel()
    }


    suspend fun logout(logout: (Boolean) -> Unit) {
        context.datastore.edit {
            it.clear()
            it[PrefKey.IS_LOGIN] = false
            logout.invoke(true)
        }
    }
}