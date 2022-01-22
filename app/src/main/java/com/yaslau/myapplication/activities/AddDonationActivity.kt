package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddDonationActivity : AppCompatActivity() {
    lateinit var typeEditText: EditText
    lateinit var idEditText: EditText
    lateinit var valueEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_donation)
        val button: Button = findViewById(R.id.addDonationButton)
        button.setOnClickListener { addDonation() }
        typeEditText = findViewById(R.id.typeEditText)
        idEditText = findViewById(R.id.idEditText)
        valueEditText = findViewById(R.id.valueEditText)
    }

    fun addDonation() {
        val sharedPref = SharedPreferencesManager(this)
        val repo = Repository()
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertDonation(charityName, idEditText.text.toString(),
                valueEditText.text.toString(), typeEditText.text.toString())
            withContext(Dispatchers.Main) {
            }
        }
    }
}