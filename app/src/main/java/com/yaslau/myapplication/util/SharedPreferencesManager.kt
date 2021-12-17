package com.yaslau.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.yaslau.myapplication.exceptions.UsernameNotSavedException


class SharedPreferencesManager(var context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    fun storeEmail(email : String){
       val editor = sharedPref.edit()
        editor.apply{
            putString("EMAIL", email)
        }.apply()
    }

    fun storeLoginStatus(loggedIn : Boolean){
        val editor = sharedPref.edit()
        editor.apply{
            putBoolean("LOGGED", loggedIn)
        }.apply()
    }

    fun retrieveEmail() : String{
        val email = sharedPref.getString("EMAIL", null)
        if (email != null) {
            return email
        }
        else throw UsernameNotSavedException()
    }

    fun retrieveLoginStatus(): Boolean {
        return sharedPref.getBoolean("LOGGED", false)
    }

}