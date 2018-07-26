package com.jwhh.notekeeper

import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.util.Log

class SettingsActivity: PreferenceActivity()
{

    override fun onBuildHeaders(target: MutableList<Header>?) {
        loadHeadersFromResource(R.xml.preference_headers, target)
    }

    override fun isValidFragment(fragmentName: String?): Boolean {
        if(TabletSettingsFragment::class.java.name.equals(fragmentName)){
            return true
        }
        return false
    }

    class TabletSettingsFragment : PreferenceFragment()
    {
		val TAG = "TabletSettingsFragment"
		
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val settings = arguments.getString("settings")
            if(settings.equals(getString(R.string.preferences_notifications))){
                addPreferencesFromResource(R.xml.preference_notifications)
            }
            else if(settings.equals(getString(R.string.preferences_sync))){
                addPreferencesFromResource(R.xml.preference_sync)
            }
            else if(settings.equals(getString(R.string.preferences_about))){
                addPreferencesFromResource(R.xml.preference_about)
            }
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
}








