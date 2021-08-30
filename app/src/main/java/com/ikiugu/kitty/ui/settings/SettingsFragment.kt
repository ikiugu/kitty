package com.ikiugu.kitty.ui.settings

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.preference.ListPreference
<<<<<<< HEAD
import androidx.preference.PreferenceFragmentCompat
=======
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
>>>>>>> origin/main
import com.ikiugu.kitty.R
import com.ikiugu.kitty.viewModels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private var settingsViewModel: SettingsViewModel? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
<<<<<<< HEAD
        setPreferencesFromResource(R.xml.user_setting_preferences, rootKey)
=======
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
//        bindPreferenceSummaryToValue(findPreference("userImageType"))
>>>>>>> origin/main

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsViewModel = ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)

        val preference = findPreference<ListPreference>("userImageType")
        preference?.setOnPreferenceChangeListener { pref, newValue ->
            val stringValue = newValue.toString()

            if (pref is ListPreference) {
                settingsViewModel?.saveNewPreference(stringValue)
            }

            true
        }
    }
<<<<<<< HEAD
=======

    private fun bindPreferenceSummaryToValue(preference: Preference?) {
        // Set the listener to watch for value changes.
        preference?.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(
            preference,
            PreferenceManager
                .getDefaultSharedPreferences(preference?.context)
                .getString(preference?.key, "")
        )
    }

    companion object {
        private val sBindPreferenceSummaryToValueListener =
            Preference.OnPreferenceChangeListener { preference, value ->
                val stringValue = value.toString()

                if (preference is ListPreference) {

                }

                true
            }

    }
>>>>>>> origin/main
}