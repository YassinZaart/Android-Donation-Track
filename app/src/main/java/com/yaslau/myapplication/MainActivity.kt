package com.yaslau.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

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
        val myIntent = Intent(this, HomePageActivity::class.java)
        startActivity(myIntent)
    }

}