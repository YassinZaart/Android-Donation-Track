package com.yaslau.myapplication.util

import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState

interface IAccountManager {
    suspend fun  signUp(name: String, email: String, password: String) : SignUpState
    suspend fun login(email: String, password: String): LoginState
    fun logout()
}