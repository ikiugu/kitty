package com.ikiugu.kitty.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
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
    }

    val imageTypeFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "Error reading shared preferences")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferenceKeys.IMAGE_TYPE_SELECTED] ?: ImageTypes.PNG.name
        }

    suspend fun updateImageTypePreferences(imageTypes: ImageTypes) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.IMAGE_TYPE_SELECTED] = imageTypes.name
        }
    }
}
