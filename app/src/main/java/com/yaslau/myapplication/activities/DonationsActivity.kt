package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.yaslau.myapplication.R

class DonationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)
        val listView = findViewById<ListView>(R.id.myView)
        val posts = ArrayList<String>()
        posts.add("Yassine El Zaart \n 10/2/2021 \n Type: Food \n Value 100$")
        posts.add("Yassine El Zaart \n 10/2/2021 \n Type: Food \n Value 100$")
        posts.add("Yassine El Zaart \n 10/2/2021 \n Type: Food \n Value 100$")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts)
        listView.setAdapter(adapter)
    }
}