package com.yaslau.myapplication.activities

import com.yaslau.myapplication.util.AccountManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val addPostButton : Button = findViewById(R.id.addPostButton)
        addPostButton.setOnClickListener { addPost() }
        val addDonationButton : Button = findViewById(R.id.addDonationsButton)
        addDonationButton.setOnClickListener { addDonation() }
        val getDonationsButton : Button = findViewById(R.id.checkDonationsButton)
        getDonationsButton.setOnClickListener { getDonations() }
        val getMyPostsButton : Button = findViewById(R.id.myPostsButton)
        getMyPostsButton.setOnClickListener { getPosts(true) }
        val getPosts : Button = findViewById(R.id.postsButton)
        getPosts.setOnClickListener { getPosts(false) }
        val logOutButton : Button = findViewById(R.id.logoutButton)
        logOutButton.setOnClickListener { logOut() }
    }
    fun addPost(){
        val myIntent = Intent(this, AddPostActivity::class.java)
        startActivity(myIntent)
    }
    fun addDonation(){
        val myIntent = Intent(this, AddDonationActivity::class.java)
        startActivity(myIntent)
    }
    fun getPosts(myPosts : Boolean){
        val myIntent = Intent(this, PostsActivity::class.java)
        myIntent.putExtra("MY_POSTS", myPosts)
        startActivity(myIntent)
    }
    fun getDonations(){
        val myIntent = Intent(this, DonationsActivity::class.java)
        startActivity(myIntent)
    }
    fun logOut(){
        val myIntent = Intent(this, LoginActivity::class.java)
        val repository = Repository()
        val sharedPref = SharedPreferencesManager(this)
        val accountManager = AccountManager(repository, sharedPref)
        accountManager.logout()
        startActivity(myIntent)
    }
}