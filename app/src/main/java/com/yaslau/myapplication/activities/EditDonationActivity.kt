package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditDonationActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var idEditText: EditText
    lateinit var valueEditText: EditText
    lateinit var descriptionEditText: EditText
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_donation)
        id = intent.getIntExtra("DONATION_ID", 1).toString()
        val editButton: Button = findViewById(R.id.editDonationButton)
        editButton.setOnClickListener { editDonation() }
        val deleteButton: Button = findViewById(R.id.deleteDonationButton)
        deleteButton.setOnClickListener { deleteDonation() }
        nameEditText = findViewById(R.id.nameEditText)
        idEditText = findViewById(R.id.idEditText)
        valueEditText = findViewById(R.id.valueEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        CoroutineScope(Dispatchers.IO).launch {
            val repo = Repository()
            val donation = repo.getDonation(id)
            withContext(Dispatchers.Main) {
                if (donation != null) {
                    nameEditText.setText(donation.name)
                    idEditText.setText(donation.donee_id)
                    descriptionEditText.setText(donation.description)
                    valueEditText.setText(donation.value.toString())
                }
            }
        }
    }

    private fun deleteDonation() {
        val repo = Repository()
        CoroutineScope(Dispatchers.IO).launch {
            repo.deleteDonation(id)
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, HomePageActivity::class.java)
                startActivity(myIntent)
            }
        }
    }

    private fun editDonation() {
        val repo = Repository()
        val sharedPref = SharedPreferencesManager(this)
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.updateDonation(id, charityName, nameEditText.text.toString(), idEditText.text.toString(),
                valueEditText.text.toString(), descriptionEditText.text.toString())
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, HomePageActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}