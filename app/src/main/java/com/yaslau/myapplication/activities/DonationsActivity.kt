package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.adapters.DonationsAdapter
import com.yaslau.myapplication.adapters.PostsAdapter
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DonationsActivity : AppCompatActivity() {

    private lateinit var recylerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        recylerview = findViewById(R.id.recyclerView)
        recylerview.layoutManager = LinearLayoutManager(this)
        recylerview.setHasFixedSize(true)
        val sharedPred = SharedPreferencesManager(this)
        val name = sharedPred.retrieveName()
        val repo = Repository()
        CoroutineScope(Dispatchers.IO).launch {
            val donations = repo.getDonations(name)
            withContext(Dispatchers.Main) {
                recylerview.adapter = donations?.let { DonationsAdapter(it) }
            }
        }
    }
}