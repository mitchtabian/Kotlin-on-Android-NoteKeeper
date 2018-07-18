package com.jwhh.notekeeper

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceActivity

class SettingsActivity: PreferenceActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.pref_main)
    }
}








