package com.yaslau.myapplication

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MyApiEndpointInterface {

    @POST("/signin")
    fun getSignupMessage(@Query(value = "email") email : String, @Query(value = "password") password : String, ) : Response<MessageDataClass>

}