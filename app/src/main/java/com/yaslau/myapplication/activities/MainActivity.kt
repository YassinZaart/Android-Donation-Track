package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.data.MessageDataClass
import com.yaslau.myapplication.repository.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signUpText : TextView = findViewById(R.id.signUpText)
        signUpText.setOnClickListener { signUp() }
        val loginButton : Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener { login() }

    }

    fun signUp(){
        val myIntent = Intent(this, SignUpActivity::class.java)
        startActivity(myIntent)
    }

    fun login(){

            CoroutineScope(Dispatchers.IO).launch {
                val response = RetrofitInstance.api.getSignInMessage("ddd", "ddd")
                if(response.isSuccessful){
                    Log.i("fsuccess", "Successful")
                }
                else {
                    Log.i("fsuccess", "Not Successful")
                    Log.i("fbody", response.code().toString())
                }
            }

       // val myIntent = Intent(this, HomePageActivity::class.java)
        //startActivity(myIntent)
    }

}