package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.dicoding.courseschedule.util.NightMode
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        findPreference<ListPreference>(getString(R.string.pref_key_dark))?.setOnPreferenceChangeListener{ pref, newTheme ->
            val mode = NightMode.valueOf(newTheme.toString().uppercase(Locale.US))
            updateTheme(mode.value)
            true
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val reminder = DailyReminder()
        findPreference<SwitchPreference>(getString(R.string.pref_key_notify))?.setOnPreferenceChangeListener{ pref, newValue ->
            if (newValue == true) {
                reminder.setDailyReminder(requireContext())
            }else {
                reminder.cancelAlarm(requireContext())
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}