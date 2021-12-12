package com.yaslau.myapplication

import android.content.Context
import android.content.SharedPreferences


class SharedPreferencesManager(var context: Context) {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    fun storeUsername(username : String){
       val editor = sharedPref.edit()
        editor.apply{
            putString("USERNAME", username)
        }.apply()
    }

    fun storeLoginStatus(loggedIn : Boolean){
        val editor = sharedPref.edit()
        editor.apply{
            putBoolean("LOGGED", loggedIn)
        }.apply()
    }

    fun retrieveUsername() : String{
        val username = sharedPref.getString("USERNAME", null)
        if (username != null) {
            return username
        }
        else throw UsernameNotSavedException()
    }

    fun retrieveLoginStatus() : Boolean{
        val logged = sharedPref.getBoolean("LOGGED", false)
        return logged
    }

}