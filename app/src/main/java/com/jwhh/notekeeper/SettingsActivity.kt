package com.jwhh.notekeeper

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.util.Log

class SettingsActivity: PreferenceActivity()
{
    private val TAG = "SettingsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onBuildHeaders(target: MutableList<Header>?) {
        loadHeadersFromResource(R.xml.preference_headers, target)
    }

    override fun isValidFragment(fragmentName: String?): Boolean {
        return true
    }

    class SettingsFragment : PreferenceFragment(),
        Preference.OnPreferenceChangeListener
    {
        private val TAG = "SettingsFragment"

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val settings = arguments.getString("settings")
            if(settings.equals(getString(R.string.preferences_notifications))){
                addPreferencesFromResource(R.xml.preference_notifications)

                val notificationsNewMessagePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message))
                notificationsNewMessagePreference.setOnPreferenceChangeListener(this)

                val notificationsRingtonePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message_ringtone))
                notificationsRingtonePreference.setOnPreferenceChangeListener(this)

                val vibratePreference: Preference = preferenceManager.findPreference(getString(R.string.key_vibrate))
                vibratePreference.setOnPreferenceChangeListener(this)
            }
            else if(settings.equals(getString(R.string.preferences_sync))){
                addPreferencesFromResource(R.xml.preference_sync)

                // set Preference Change Listeners
                val galleryNamePreference: Preference = preferenceManager.findPreference(getString(R.string.key_gallery_name))
                galleryNamePreference.setOnPreferenceChangeListener(this)

                val uploadWifiPreference: Preference = preferenceManager.findPreference(getString(R.string.key_upload_over_wifi))
                uploadWifiPreference.setOnPreferenceChangeListener(this)

                val backupFrequencyPreference: Preference = preferenceManager.findPreference(getString(R.string.key_backup_frequency))
                backupFrequencyPreference.setOnPreferenceChangeListener(this)
            }
            else if(settings.equals(getString(R.string.preferences_about))){
                addPreferencesFromResource(R.xml.preference_about)
            }


        }

        override fun onPreferenceChange(p0: Preference?, key: Any?): Boolean {
            printToLog("Preference change detected")
            when(key){
                getString(R.string.key_gallery_name) -> updatePreferenceSuccess(getString(R.string.key_gallery_name))
                getString(R.string.key_upload_over_wifi) -> updatePreferenceSuccess(getString(R.string.key_upload_over_wifi))
                getString(R.string.key_notifications_new_message) -> updatePreferenceSuccess(getString(R.string.key_notifications_new_message))
                getString(R.string.key_notifications_new_message_ringtone) -> updatePreferenceSuccess(getString(R.string.key_notifications_new_message_ringtone))
                getString(R.string.key_vibrate) -> updatePreferenceSuccess(getString(R.string.key_vibrate))
                getString(R.string.key_backup_frequency) -> updatePreferenceSuccess(getString(R.string.key_backup_frequency))
            }

            return true // Update the state of the preference with the new value
        }

        fun updatePreferenceSuccess(key: String?){

            // If this was a real application we would send the updates to server here
            uploadPreferencesToServer()

            printToLog("successfully updated preferences. key: " + key)

        }

        private fun uploadPreferencesToServer(){
            // Code for uploading updated preferences to server
        }

        private fun printToLog(message: String?){
            Log.d(TAG, message)
        }

    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }

}








