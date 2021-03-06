package com.jwhh.notekeeper

import android.content.Context
import android.content.SharedPreferences
import android.os.*
import android.preference.PreferenceManager


import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.layout_account_toolbar.*
import java.util.*


class AccountActivity : AppCompatActivity(),
        View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener
{
    private val TAG = "AccountFragment"

    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        input_phone_number.addTextChangedListener(PhoneNumberFormattingTextWatcher(Locale.getDefault().country))

        initWidgetValues()
        initToolbar()
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, key: String?) {
        when(key){
            PREFERENCES_NAME -> updatePreferenceSuccess(PREFERENCES_NAME)
            PREFERENCES_USERNAME -> updatePreferenceSuccess(PREFERENCES_USERNAME)
            PREFERENCES_PHONE_NUMBER -> updatePreferenceSuccess(PREFERENCES_PHONE_NUMBER)
            PREFERENCES_EMAIL -> updatePreferenceSuccess(PREFERENCES_EMAIL)
            PREFERENCES_GENDER -> updatePreferenceSuccess(PREFERENCES_GENDER)
        }
    }

    fun updatePreferenceSuccess(key: String?){
        showProgressBar()

        // Simulate uploading the new data to server
        if(!isRunning){

            // If this was a real application we would send the updates to server here
            simulateUploadToServer()

            Snackbar.make(currentFocus.rootView, "sending updates to server", Snackbar.LENGTH_SHORT).show()
            printToLog("successfully updated shared preferences. key: " + key)
        }
    }

    private fun simulateUploadToServer(){
        val handler = Handler(Looper.getMainLooper())
        val start: Long = System.currentTimeMillis()
        val runnable: Runnable = object: Runnable{
            override fun run() {
                handler.postDelayed(this, 100)
                isRunning = true
                val now: Long = System.currentTimeMillis()
                val difference: Long = now - start
                if(difference >= 1000){
                    printToLog("update finished")
                    updateFinished()
                    handler.removeCallbacks(this)
                    isRunning = false
                }
            }
        }
        this.runOnUiThread(runnable)
    }

    override fun onClick(widget: View?) {
        when(widget?.id){

            R.id.close -> finish()

            R.id.save -> savePreferences()

        }

    }

    private fun updateFinished(){
        hideProgressBar()
    }

    private fun initWidgetValues(){
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val name = prefs.getString(PREFERENCES_NAME, "John Doe")
        val username = prefs.getString(PREFERENCES_USERNAME, "John.Doe")
        val email = prefs.getString(PREFERENCES_EMAIL, "John.Doe@defaultemail.com")
        val phoneNum = prefs.getString(PREFERENCES_PHONE_NUMBER, "1 604 855-1111")
        val gender = prefs.getString(PREFERENCES_GENDER, "Male")

        input_name.setText(name)
        input_username.setText(username)
        input_email_address.setText(email)
        input_phone_number.setText(phoneNum)

        val genders = resources.getStringArray(R.array.gender_array)
        for (index in 0.. genders.size){
            if(genders.get(index).equals(gender)){
                gender_spinner.setSelection(index)
                break
            }
        }

    }

    fun savePreferences(){
        currentFocus.rootView.hideKeyboard()

        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = prefs.edit()

        // Name
        if(!input_name.text.toString().equals("")){
            val name: String? = input_name.text.toString()
            printToLog("saving name: ${name}")
            editor.putString(PREFERENCES_NAME, name)
            editor.apply()

        }

        // Username
        if(!input_username.text.toString().equals("")){
            val username: String? = input_username.text.toString()
            printToLog("saving username: ${username}")
            editor.putString(PREFERENCES_USERNAME, username)
            editor.apply()

        }

        // Email
        if(!input_email_address.text.toString().equals("")){
            val email: String? = input_email_address.text.toString()
            printToLog("saving username: ${email}")
            editor.putString(PREFERENCES_EMAIL, email)
            editor.apply()

        }

        // Phone
        if(!input_phone_number.text.toString().equals("")){
            val phoneNumber: String? = input_phone_number.text.toString()
            printToLog("saving phone number: ${phoneNumber}")
            editor.putString(PREFERENCES_PHONE_NUMBER, phoneNumber)
            editor.apply()

        }

        // Gender
        printToLog("saving gender: " + gender_spinner.selectedItem.toString())
        editor.putString(PREFERENCES_GENDER, gender_spinner.selectedItem.toString())
    }

    private fun initToolbar() {
        close.setOnClickListener(this)
        save.setOnClickListener(this)
    }

    private fun removeNumberFormatting(number: String): String{
        val regex = Regex("[^0-9]")
        return regex.replace(number, "")
    }

    fun View.hideKeyboard() {
        printToLog("closing keyboard")
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun showProgressBar(){
        save.visibility = View.INVISIBLE
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        progress_bar.visibility = View.INVISIBLE
        save.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun printToLog(message: String?){
        Log.d(TAG, message)
    }
}















