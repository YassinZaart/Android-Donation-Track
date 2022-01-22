package com.yaslau.myapplication.util

interface ISharedPref {
    fun storeEmail(email : String)
    fun storeLoginStatus(loggedIn : Boolean)
    fun retrieveEmail() : String
    fun retrieveLoginStatus(): Boolean
    fun retrieveName() : String
    fun storeName(name : String)
}