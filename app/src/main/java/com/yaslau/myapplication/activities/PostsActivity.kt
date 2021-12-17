package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ArrayAdapter
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.ModelToList
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        val listView = findViewById<ListView>(R.id.myView)
        var posts = ArrayList<String>()
        val sharedPred = SharedPreferencesManager(this)
        val name = sharedPred.retrieveName()
        Log.i("test", name)
        val repo = Repository()
        CoroutineScope(Dispatchers.IO).launch {
            val p = repo.getPosts(name)
            posts = p?.let { ModelToList(it).toArrayList() }!!
            withContext(Dispatchers.Main) {}
        }
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts)
        listView.setAdapter(adapter)

    }
}