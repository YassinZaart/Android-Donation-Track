package com.yaslau.myapplication.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api : MyApiEndpointInterface by lazy{
        Retrofit.Builder()
            .baseUrl("https://donation-track-backend.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiEndpointInterface::class.java)
    }
}