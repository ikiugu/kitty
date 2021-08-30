package com.ikiugu.kitty.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ikiugu.kitty.util.ImageTypes
import com.ikiugu.kitty.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Alfred Ikiugu on 30/08/2021
 */

@HiltViewModel
class SettingsViewModel @Inject constructor(private val preferenceManager: PreferenceManager) :
    ViewModel() {

    fun saveNewListPreference(imageTypes: String) {
        val type = when (imageTypes) {
            "PNG" -> ImageTypes.PNG
            "JPG" -> ImageTypes.JPG
            "GIF" -> ImageTypes.GIF
            else -> ImageTypes.JPG
        }

        viewModelScope.launch {
            preferenceManager.updateImageTypePreferences(type)
        }
    }

    fun saveUsernamePreference(username: String) {
        viewModelScope.launch {
            preferenceManager.updateUsernamePreference(username)
        }
    }
}