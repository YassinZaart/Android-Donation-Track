package com.yaslau.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.yaslau.myapplication.exceptions.UsernameNotSavedException


class SharedPreferencesManager(var context: Context) : ISharedPref {

    private val sharedPref: SharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    override fun storeEmail(email : String){
       val editor = sharedPref.edit()
        editor.apply{
            putString("EMAIL", email)
        }.apply()
    }

    override fun storeLoginStatus(loggedIn : Boolean){
        val editor = sharedPref.edit()
        editor.apply{
            putBoolean("LOGGED", loggedIn)
        }.apply()
    }

    override fun retrieveEmail() : String{
        val email = sharedPref.getString("EMAIL", null)
        if (email != null) {
            return email
        }
        else throw UsernameNotSavedException()
    }

    override fun retrieveLoginStatus(): Boolean {
        return sharedPref.getBoolean("LOGGED", false)
    }

    override fun retrieveName() : String{
        val name = sharedPref.getString("NAME", null)
        if (name != null) {
            return name
        }
        else throw UsernameNotSavedException()
    }

    override fun storeName(name : String){
        val editor = sharedPref.edit()
        editor.apply{
            putString("NAME", name)
        }.apply()
    }


}