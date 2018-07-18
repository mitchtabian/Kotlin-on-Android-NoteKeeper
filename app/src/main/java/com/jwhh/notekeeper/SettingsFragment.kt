package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SettingsFragment: PreferenceFragment(),
        Preference.OnPreferenceClickListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.pref_main)

        // Set Preference Click Listener
        val accountPreference: Preference = findPreference(getString(R.string.key_account_settings))
        accountPreference.setOnPreferenceClickListener {onPreferenceClick(it)}

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = super.onCreateView(inflater, container, savedInstanceState)
        view.setBackgroundColor(ContextCompat.getColor(activity, R.color.white))

        return view
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {

        if(preference!!.key.equals(getString(R.string.key_account_settings))){
            val activityIntent = Intent(activity, AccountActivity::class.java)
            startActivity(activityIntent)
        }

        return true
    }
}















