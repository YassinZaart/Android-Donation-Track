package com.yaslau.myapplication.repository

import com.yaslau.myapplication.data.MessageDataClass
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MyApiEndpointInterface {

    @POST("/login")
    suspend fun getSignInMessage(@Query(value = "email") email : String,
                         @Query(value = "password") password : String) : Response<MessageDataClass>

    @POST("/signup")
    suspend fun signUp(@Query(value = "email") email: String, @Query(value = "password") password : String,
                       @Query(value = "name") name : String, @Query(value = "phone_number") phoneNumber : String,
                       @Query(value = "city") city : String, @Query(value = "street") street : String) : Response<MessageDataClass>
}