package com.yaslau.myapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.adapters.PostsAdapter
import com.yaslau.myapplication.data.PostData
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PostsActivity : AppCompatActivity() {

    private lateinit var recylerview : RecyclerView
    private lateinit var posts : List<PostData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        recylerview = findViewById(R.id.recyclerView)
        recylerview.layoutManager = LinearLayoutManager(this)
        recylerview.setHasFixedSize(true)
        val isMyPost = intent.getBooleanExtra("MY_POSTS", true)
        val sharedPred = SharedPreferencesManager(this)
        val name = sharedPred.retrieveName()
        val repo = Repository()
        CoroutineScope(Dispatchers.IO).launch {
            posts = if(isMyPost){
                repo.getPosts(name)!!
            } else {
                repo.getPosts()!!
            }
            withContext(Dispatchers.Main) {
                recylerview.adapter = posts.let { PostsAdapter(it) }
            }
        }
    }
}