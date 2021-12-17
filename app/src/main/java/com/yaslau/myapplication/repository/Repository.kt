package com.yaslau.myapplication.repository

import android.util.Log
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {

    suspend fun login(email : String, password : String) : LoginState{
            val response = RetrofitInstance.api.getSignInMessage(email, password)
            return when(response.code()){
                200 -> LoginState.SUCCESS
                404 -> LoginState.INVALID_EMAIL
                409 -> LoginState.WRONG_PASSWORD
                else -> LoginState.API_ERROR
            }
    }

    suspend fun signup(email : String, password : String, name : String
                       , phone_number : String, city : String,
                       street : String) : SignUpState{
        val response = RetrofitInstance.api.signUp(email, password, name, phone_number, city, street)
        return when(response.code()){
            200 -> SignUpState.SUCCESS
            409 -> SignUpState.USER_EXISTS
            else -> SignUpState.API_ERROR
        }
    }


 }
