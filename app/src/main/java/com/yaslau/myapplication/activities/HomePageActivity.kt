package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yaslau.myapplication.R

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val addPost : Button = findViewById(R.id.addPostButton)
        addPost.setOnClickListener { addPost() }
        val addDonation : Button = findViewById(R.id.addDonationsButton)
        addDonation.setOnClickListener { addDonation() }
        val getDonations : Button = findViewById(R.id.checkDonationsButton)
        getDonations.setOnClickListener { getDonations() }
        val getPosts : Button = findViewById(R.id.postsButton)
        getPosts.setOnClickListener { getPosts() }
    }
    fun addPost(){
        val myIntent = Intent(this, AddPostActivity::class.java)
        startActivity(myIntent)
    }
    fun addDonation(){
        val myIntent = Intent(this, AddDonationActivity::class.java)
        startActivity(myIntent)
    }
    fun getPosts(){
        val myIntent = Intent(this, PostsActivity::class.java)
        startActivity(myIntent)
    }
    fun getDonations(){
        val myIntent = Intent(this, DonationsActivity::class.java)
        startActivity(myIntent)
    }
}