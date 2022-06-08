package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.states.SignUpState
import com.yaslau.myapplication.util.AccountManager
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {

    lateinit var emailText: EditText
    lateinit var passwordText: EditText
    lateinit var nameText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        val signInButton : Button = findViewById(R.id.signInButton)
        signInButton.setOnClickListener { signIn() }
        val signUpButton : Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener { signUp() }
        nameText = findViewById(R.id.usernameEditText)
        emailText = findViewById(R.id.emailEditText)
        passwordText = findViewById(R.id.passwordEditText)
    }

    fun signIn(){
        val myIntent = Intent(this, LoginActivity::class.java)
        startActivity(myIntent)
    }

    fun signUp(){
        val repository = Repository()
        val sharedPref = SharedPreferencesManager(this)
        val accountManager = AccountManager(repository, sharedPref)
        CoroutineScope(Dispatchers.IO).launch {
            val state =
                accountManager.signUp(nameText.text.toString(), emailText.text.toString(), passwordText.text.toString())
            withContext(Dispatchers.Main) {
                Log.i("hi", nameText.text.toString()+  emailText.text.toString()+passwordText.text.toString() )
                when (state) {
                    SignUpState.USER_EXISTS -> Toast.makeText(
                        applicationContext,
                        "Email already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                    SignUpState.SUCCESS -> {
                        Toast.makeText(
                            applicationContext,
                            "Signed Up!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val myIntent = Intent(this, SignUpActivity::class.java)
                        startActivity(myIntent)
                    }
                }
            }

        }

    }

    private fun Intent(coroutineScope: CoroutineScope, java: Class<SignUpActivity>): Intent {
        return Intent(this, LoginActivity::class.java)
    }

}