package com.ikiugu.kitty.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.ikiugu.kitty.R
import com.ikiugu.kitty.viewModels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private var settingsViewModel: SettingsViewModel? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.user_setting_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsViewModel = ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)

        val listPreference = findPreference<ListPreference>("userImageType")
        listPreference?.setOnPreferenceChangeListener { pref, newValue ->
            val stringValue = newValue.toString()

            if (pref is ListPreference) { // not needed because the listener is attached to the listPreference
                settingsViewModel?.saveNewListPreference(stringValue)
            }

            true
        }

        val usernamePreference = findPreference<EditTextPreference>("username")
        usernamePreference?.setOnPreferenceChangeListener { pref, newValue ->
            val stringValue = newValue.toString()

            if (pref is EditTextPreference) { // not needed because the listener is attached to the EditTextPreference
                settingsViewModel?.saveUsernamePreference(stringValue)
            }

            true
        }
    }
}