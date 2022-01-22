package com.yaslau.myapplication.repository

import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState

interface RepositoryInterface {

    suspend fun login(email : String, password : String) : LoginState
    suspend fun signup(email : String, password : String, name : String
                       , phone_number : String, city : String,
                       street : String) : SignUpState
    suspend fun getUserName(email: String) : String?
    suspend fun insertPost(charityName: String, name: String, phone_number: String, address: String, description: String )
    suspend fun getPosts(charityName: String) : List<PostData>?
    suspend fun getPosts() : List<PostData>?
    suspend fun getDonations(charityName: String) : List<DonationData>?
    suspend fun insertDonation(charityName: String, id: String, value: String, type: String)
}