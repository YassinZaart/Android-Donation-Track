package com.yaslau.myapplication.util

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState

class AccountManager(var context: Context) {

    private val repository = Repository()
    private val sharedPref = SharedPreferencesManager(context)

    suspend fun signUp(name: String, email: String, password: String) : SignUpState{
        val state = repository.signup(email, password, name, "N/A", "N/A", "N/A")
        return state
    }

    suspend fun login(email: String, password: String): LoginState{
        val state = repository.login(email, password)
        val name = repository.getUserName(email)
        if(state == LoginState.SUCCESS){
            sharedPref.storeLoginStatus(true)
            sharedPref.storeEmail(email)
            if (name != null) {
                sharedPref.storeName(name)
                Log.i("null", name)
            }else{
                Log.i("null", "null")
            }
        }
        return state
    }

    fun logout(){
        sharedPref.storeLoginStatus(false)
        sharedPref.storeEmail("N/A")
    }
}