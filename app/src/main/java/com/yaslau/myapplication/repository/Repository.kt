package com.yaslau.myapplication.repository

import android.util.Log
import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.states.SignUpState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository : RepositoryInterface {

    override suspend fun login(email : String, password : String) : LoginState{
            val response = RetrofitInstance.api.getSignInMessage(email, password)
            return when(response.code()){
                200 -> LoginState.SUCCESS
                404 -> LoginState.INVALID_EMAIL
                409 -> LoginState.WRONG_PASSWORD
                else -> LoginState.API_ERROR
            }
    }

    override suspend fun signup(email : String, password : String, name : String
                                ) : SignUpState{
        val response = RetrofitInstance.api.signUp(email, password, name)
        return when(response.code()){
            200 -> SignUpState.SUCCESS
            409 -> SignUpState.USER_EXISTS
            else -> SignUpState.API_ERROR
        }
    }

    override suspend fun getUserName(email: String) : String?{
        val response = RetrofitInstance.api.getUser(email)
        return response.body()?.name
    }

    override suspend fun insertPost(charityName: String, name: String, phone_number: String,
                                    address: String, value: String, description: String ){
        RetrofitInstance.api.insertPost(charityName, name, phone_number, address, value, description)
    }

    override suspend fun updatePost(
        postId: String,
        charityName: String,
        name: String,
        phone_number: String,
        address: String,
        value: String,
        description: String
    ) {
        RetrofitInstance.api.updatePost(postId, charityName, name, phone_number, address, value, description)
    }

    override suspend fun deletePost(postId: String) {
        RetrofitInstance.api.deletePost(postId)
    }

    override suspend fun deleteDonation(donationID: String) {
        RetrofitInstance.api.deleteDonation(donationID)
    }

    override suspend fun updateDonation(
        donation_id: String,
        charityName: String,
        name: String,
        id: String,
        value: String,
        description: String
    ) {
        RetrofitInstance.api.updateDonation(donation_id, charityName, name, id, value, description)
    }

    override suspend fun getPosts(charityName: String) : List<PostData>?{
        val response = RetrofitInstance.api.getPosts(charityName)
        return response.body()
    }

    override suspend fun getPosts() : List<PostData>?{
        val response = RetrofitInstance.api.getPosts()
        return response.body()
    }

    override suspend fun getPost(postID: String): PostData? {
        val response = RetrofitInstance.api.getPost(postID)
        return response.body()
    }

    override suspend fun getDonation(donationID: String): DonationData? {
        val response = RetrofitInstance.api.getDonation(donationID)
        return response.body()
    }

    override suspend fun getDonations(charityName: String) : List<DonationData>?{
        val response = RetrofitInstance.api.getDonations(charityName)
        return response.body()
    }

    override suspend fun getDonationsByID(donee_id: String): List<DonationData>? {
        val  response = RetrofitInstance.api.getDonationsByID(donee_id)
        return response.body()
    }

    override suspend fun insertDonation(charityName: String, name: String, id: String, value: String, description: String){
        RetrofitInstance.api.insertDonation(charityName, name, id, value, description)
    }

    override suspend fun addContribution(username: String, id: String, value: String) {
        RetrofitInstance.api.addContribution(username, id, value)
    }

}
