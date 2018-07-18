package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment

import android.support.v4.content.ContextCompat
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.Toolbar

class SettingsFragment: PreferenceFragment(),
        Preference.OnPreferenceClickListener,
        Preference.OnPreferenceChangeListener
{

    private val TAG = "SettingsFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_main)

        // Set Preference Click Listener
        val accountPreference: Preference = findPreference(getString(R.string.key_account_settings))
        accountPreference.setOnPreferenceClickListener {onPreferenceClick(it)}

        // set Preference Change Listeners
        val galleryNamePreference: Preference = preferenceManager.findPreference(getString(R.string.key_gallery_name))
        galleryNamePreference.setOnPreferenceChangeListener(this)

        val uploadWifiPreference: Preference = preferenceManager.findPreference(getString(R.string.key_upload_over_wifi))
        uploadWifiPreference.setOnPreferenceChangeListener(this)

        val notificationsNewMessagePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message))
        notificationsNewMessagePreference.setOnPreferenceChangeListener(this)

        val notificationsRingtonePreference: Preference = preferenceManager.findPreference(getString(R.string.key_notifications_new_message_ringtone))
        notificationsRingtonePreference.setOnPreferenceChangeListener(this)

        val vibratePreference: Preference = preferenceManager.findPreference(getString(R.string.key_vibrate))
        vibratePreference.setOnPreferenceChangeListener(this)

        val backupFrequencyPreference: Preference = preferenceManager.findPreference(getString(R.string.key_backup_frequency))
        backupFrequencyPreference.setOnPreferenceChangeListener(this)

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = super.onCreateView(inflater, container, savedInstanceState)
        view.setBackgroundColor(ContextCompat.getColor(activity, R.color.white))

        return view
    }

    override fun onPreferenceChange(preference: Preference?, key: Any?): Boolean {
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

    override fun onPreferenceClick(preference: Preference?): Boolean {

        if(preference!!.key.equals(getString(R.string.key_account_settings))){
            val activityIntent = Intent(activity, AccountActivity::class.java)
            startActivity(activityIntent)
        }

        return true
    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
}















