package com.ikiugu.kitty.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ikiugu.kitty.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


/**
 * Created by Alfred Ikiugu on 29/08/2021
 */


enum class ImageTypes { GIF, PNG, JPG }

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class PreferenceManager @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val IMAGE_TYPE_SELECTED = stringPreferencesKey("IMAGE_TYPE_SELECTED")
        val USER_NAME_SELECTED = stringPreferencesKey("USERNAME_SELECTED")
    }

    val imageTypeFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, context.getString(R.string.shared_preferences_error))
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.IMAGE_TYPE_SELECTED] ?: ImageTypes.PNG.name
        }

    val usernameFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, context.getString(R.string.shared_preferences_error))
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.USER_NAME_SELECTED] ?: "ikiugu"
        }

    suspend fun updateImageTypePreferences(imageType: ImageTypes) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.IMAGE_TYPE_SELECTED] = imageType.name
        }
    }

    suspend fun updateUsernamePreference(username: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.USER_NAME_SELECTED] = username
        }
    }
}
