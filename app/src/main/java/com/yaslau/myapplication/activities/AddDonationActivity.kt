package com.yaslau.myapplication.activities

import android.content.Intent
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
    lateinit var nameEditText: EditText
    lateinit var idEditText: EditText
    lateinit var valueEditText: EditText
    lateinit var descriptionEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_donation)
        val button: Button = findViewById(R.id.addDonationButton)
        button.setOnClickListener { addDonation() }
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        valueEditText = findViewById(R.id.valueEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
    }

    fun addDonation() {
        val sharedPref = SharedPreferencesManager(this)
        val repo = Repository()
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.insertDonation(charityName, nameEditText.text.toString(), idEditText.text.toString(),
                valueEditText.text.toString(), descriptionEditText.text.toString())
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, HomePageActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}