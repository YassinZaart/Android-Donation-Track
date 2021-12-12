package com.yaslau.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val signInText : TextView = findViewById(R.id.signInText)
        signInText.setOnClickListener { signIn() }
        val signUpButton : Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener { signUp() }
    }

    fun signIn(){
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

    fun signUp(){
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
}