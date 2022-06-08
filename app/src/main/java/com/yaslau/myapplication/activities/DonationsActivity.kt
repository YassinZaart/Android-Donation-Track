package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.adapters.DonationsAdapter
import com.yaslau.myapplication.adapters.PostsAdapter
import com.yaslau.myapplication.data.DonationData
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Array

class DonationsActivity : AppCompatActivity() {

    private lateinit var recylerview : RecyclerView
    private lateinit var donations: List<DonationData>
    private lateinit var searchView: SearchView
    private lateinit var adapter: DonationsAdapter
    private var isMyDonation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)
        recylerview = findViewById(R.id.recyclerView)
        recylerview.layoutManager = LinearLayoutManager(this)
        recylerview.setHasFixedSize(true)
        val sharedPred = SharedPreferencesManager(this)
        val name = sharedPred.retrieveName()
        isMyDonation = intent.getBooleanExtra("MY_DONATIONS", false)
        val repo = Repository()
        searchView = findViewById(R.id.search_bar)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterList(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return false
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            donations = repo.getDonations(name)!!
            withContext(Dispatchers.Main) {
                adapter = DonationsAdapter(ArrayList())
                recylerview.adapter = adapter
                if (isMyDonation) adapter = DonationsAdapter(donations)
                adapter.setOnItemClickListener(object : DonationsAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        if(isMyDonation) {
                            val myIntent =
                                Intent(applicationContext, EditDonationActivity::class.java)
                            myIntent.putExtra("DONATION_ID", donations[position].donation_id)
                            startActivity(myIntent)
                        }
                    }

                    })
                    recylerview.adapter = adapter

            }
        }
    }

    fun filterList(newText: String) {
        val repo = Repository()
        if(!isMyDonation) {
            CoroutineScope(Dispatchers.IO).launch {
                donations = repo.getDonationsByID(newText)!!
                adapter.newsList = donations
            }
        }
        else {
            val filteredList = ArrayList<DonationData>()
            for(donation in donations){
                if (donation.donation_id.toString().contains(newText.lowercase())){
                    filteredList.add(donation)
                }
            }
            adapter.newsList = filteredList
        }

    }

}