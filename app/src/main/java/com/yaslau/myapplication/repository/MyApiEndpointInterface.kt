package com.yaslau.myapplication.repository

import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.MessageDataClass
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.data.UserData
import retrofit2.Response
import retrofit2.http.*

interface MyApiEndpointInterface {

    @POST("/login")
    suspend fun getSignInMessage(@Query(value = "email") email : String,
                         @Query(value = "password") password : String) : Response<MessageDataClass>

    @POST("/signup")
    suspend fun signUp(@Query(value = "email") email: String, @Query(value = "password") password : String,
                       @Query(value = "user_name") name : String) : Response<MessageDataClass>

    @GET("/users")
    suspend fun getUser(@Query(value = "email") email : String) : Response<UserData>

    @POST("/posts")
    suspend fun insertPost(@Query(value = "charity_name") charityName: String,
                       @Query(value = "name") name : String, @Query(value = "phone_number") phoneNumber : String,
                       @Query(value = "address") address : String, @Query(value = "value") value: String,
                           @Query(value = "description") description : String) : Response<MessageDataClass>
    @PATCH("/posts")
    suspend fun updatePost(@Query(value = "post_id") post_id: String,
                               @Query(value = "charity_name") charityName: String, @Query(value = "name") name: String,
                               @Query(value = "phone_number") id: String,  @Query(value = "address") address: String,
                               @Query(value = "value") value: String,
                               @Query(value = "description") description: String) : Response<MessageDataClass>

    @DELETE("/posts")
    suspend fun deletePost(@Query(value = "post_id") donation_id: String) : Response<MessageDataClass>

    @GET("/posts")
    suspend fun getPosts(@Query(value = "charity_name")name: String): Response<List<PostData>>

    @GET("/posts")
    suspend fun getPost(@Query(value = "post_id")name: String): Response<PostData>

    @GET("/posts")
    suspend fun getPosts(): Response<List<PostData>>

    @GET("/donations")
    suspend fun getDonations(@Query(value = "user_name")name: String): Response<List<DonationData>>

    @GET("/donations")
    suspend fun getDonationsByID(@Query(value = "donee_id")id: String): Response<List<DonationData>>

    @GET("/donations")
    suspend fun getDonation(@Query(value = "donation_id")name: String): Response<DonationData>

    @POST("/donations")
    suspend fun insertDonation(@Query(value = "user_name") charityName: String, @Query(value = "name") name: String,
                               @Query(value = "id") id: String,  @Query(value = "value") value: String,
                               @Query(value = "description") description: String) : Response<MessageDataClass>

    @PATCH("/donations")
    suspend fun updateDonation(@Query(value = "donation_id") donation_id: String,
                               @Query(value = "user_name") charityName: String, @Query(value = "name") name: String,
                               @Query(value = "id") id: String,  @Query(value = "value") value: String,
                               @Query(value = "description") description: String) : Response<MessageDataClass>

    @DELETE("/donations")
    suspend fun deleteDonation(@Query(value = "donation_id") donation_id: String) : Response<MessageDataClass>

    @PUT("/posts/contributions")
    suspend fun addContribution(@Query(value = "user_name") username : String, @Query(value = "post_id") id : String,
                                @Query(value = "value") value : String)
}
