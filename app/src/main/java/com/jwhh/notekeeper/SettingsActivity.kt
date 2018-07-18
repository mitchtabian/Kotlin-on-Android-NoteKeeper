package com.jwhh.notekeeper

import android.app.FragmentTransaction
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.provider.Settings

class SettingsActivity: PreferenceActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        addPreferencesFromResource(R.xml.pref_main)
    }

    override fun onBuildHeaders(target: MutableList<Header>?) {
        loadHeadersFromResource(R.xml.preference_headers, target)
    }
    
	class TabletSettingsFragment : PreferenceFragment()
    {

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
    }
}








