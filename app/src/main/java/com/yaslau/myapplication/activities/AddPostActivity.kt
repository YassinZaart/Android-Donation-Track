package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.AccountManager
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPostActivity : AppCompatActivity() {

    lateinit var usernameEditText: EditText
    lateinit var phonNum: EditText
    lateinit var address: EditText
    lateinit var description: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        val button: Button = findViewById(R.id.addPostButton)
        button.setOnClickListener { addPost() }
        usernameEditText = findViewById(R.id.usernameEditText)
        phonNum = findViewById(R.id.phoneNum)
        address = findViewById(R.id.addressEditText)
        description = findViewById(R.id.description)
    }

    fun addPost() {
        val sharedPref = SharedPreferencesManager(this)
        val repo = Repository()
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertPost(charityName, usernameEditText.text.toString(),
            phonNum.text.toString(), address.text.toString(), description.text.toString())
            withContext(Dispatchers.Main) {
            }
        }
    }
}