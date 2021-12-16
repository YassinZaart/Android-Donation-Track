package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.ArrayAdapter
import com.yaslau.myapplication.R


class Posts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        val listView = findViewById<ListView>(R.id.myView)
        val posts = ArrayList<String>()
        posts.add("Yassine El Zaart \n Beirut \n 81899268 \n Need immediate help ")
        posts.add("Yassine El Zaart \n Beirut \n 81899268 \n Need immediate help ")

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts)
        listView.setAdapter(adapter)

    }
}