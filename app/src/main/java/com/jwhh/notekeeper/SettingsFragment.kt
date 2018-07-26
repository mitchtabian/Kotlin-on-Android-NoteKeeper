package com.jwhh.notekeeper

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log

class SettingsFragment: PreferenceFragment(),
        Preference.OnPreferenceClickListener
{

	val TAG = "SettingsFragment"
	
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















