package com.yaslau.myapplication.repository

import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.MessageDataClass
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.data.UserData
import retrofit2.Response
import retrofit2.http.GET
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

    @GET("/users")
    suspend fun getUser(@Query(value = "email") email : String) : Response<UserData>

    @POST("/posts")
    suspend fun insertPost(@Query(value = "charity_name") charityName: String,
                       @Query(value = "name") name : String, @Query(value = "phone_number") phoneNumber : String,
                       @Query(value = "address") address : String, @Query(value = "description") description : String) : Response<MessageDataClass>

    @GET("/posts")
    suspend fun getPosts(@Query(value = "charity_name")name: String): Response<List<PostData>>

    @GET("/posts")
    suspend fun getPosts(): Response<List<PostData>>

    @GET("/donations")
    suspend fun getDonations(@Query(value = "user_name")name: String): Response<List<DonationData>>

    @POST("/donations")
    suspend fun insertDonation(@Query(value = "user_name") charityName: String,
                               @Query(value = "donee_id") id: String,  @Query(value = "value") value: String,
                               @Query(value = "type") description: String) : Response<MessageDataClass>

}
