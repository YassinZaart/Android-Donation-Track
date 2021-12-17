package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yaslau.myapplication.R
import com.yaslau.myapplication.states.LoginState
import com.yaslau.myapplication.util.AccountManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var emailText: EditText
    lateinit var passwordText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signUpText : TextView = findViewById(R.id.signUpText)
        signUpText.setOnClickListener { signUp() }
        val loginButton : Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener { login() }
        emailText = findViewById(R.id.emailEditText)
        passwordText = findViewById(R.id.passwordEditText)

    }

    fun signUp(){
        val myIntent = Intent(this, SignUpActivity::class.java)
        startActivity(myIntent)
    }

    fun login(){
        val accountManager = AccountManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            val state =
                accountManager.login(emailText.text.toString(), passwordText.text.toString())
            withContext(Dispatchers.Main) {

                when (state) {
                    LoginState.WRONG_PASSWORD -> Toast.makeText(
                        applicationContext,
                        "Wrong Password",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginState.INVALID_EMAIL -> Toast.makeText(
                        applicationContext,
                        "Invalid Email",
                        Toast.LENGTH_SHORT
                    ).show()
                    LoginState.SUCCESS -> {
                        Toast.makeText(
                            applicationContext,
                            "Logged in!",
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
        return Intent(this, HomePageActivity::class.java)
    }

}