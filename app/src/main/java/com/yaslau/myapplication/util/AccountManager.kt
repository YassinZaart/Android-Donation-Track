package com.yaslau.myapplication.util

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.repository.RepositoryInterface
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState

class AccountManager(val repository: RepositoryInterface,
                     val sharedPref : ISharedPref): IAccountManager {

    override suspend fun signUp(name: String, email: String, password: String) : SignUpState{
        val state = repository.signup(email, password, name)
        return state
    }

    override suspend fun login(email: String, password: String): LoginState{
        val state = repository.login(email, password)
        val name = repository.getUserName(email)
        if(state == LoginState.SUCCESS){
            sharedPref.storeLoginStatus(true)
            sharedPref.storeEmail(email)
            if (name != null) {
                sharedPref.storeName(name)
            }
        }
        return state
    }

    override fun logout(){
        sharedPref.storeLoginStatus(false)
        sharedPref.storeEmail("N/A")
    }
}