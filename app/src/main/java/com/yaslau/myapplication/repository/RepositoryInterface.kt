package com.yaslau.myapplication.repository

import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState
import retrofit2.http.Query

interface RepositoryInterface {

    suspend fun login(email : String, password : String) : LoginState
    suspend fun signup(email : String, password : String, name : String) : SignUpState
    suspend fun getUserName(email: String) : String?
    suspend fun insertPost(charityName: String, name: String, phone_number: String, address: String,
                           value: String, description: String )
    suspend fun updatePost(postId: String, charityName: String, name: String, phone_number: String, address: String, value: String, description: String )
    suspend fun deletePost(postId: String)
    suspend fun deleteDonation(donationID: String)
    suspend fun updateDonation(donation_id: String, charityName: String, name: String, id: String, value: String, description: String)
    suspend fun getPosts(charityName: String) : List<PostData>?
    suspend fun getPosts() : List<PostData>?
    suspend fun getPost(postID: String) : PostData?
    suspend fun getDonation(donationID: String) : DonationData?
    suspend fun getDonations(charityName: String) : List<DonationData>?
    suspend fun getDonationsByID(donee_id: String) : List<DonationData>?
    suspend fun insertDonation(charityName: String, name: String, id: String, value: String, description: String)
    suspend fun addContribution(username : String,id : String, value : String)
}