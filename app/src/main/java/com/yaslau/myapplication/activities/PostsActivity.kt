package com.yaslau.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
    private lateinit var searchView: SearchView
    private lateinit var adapter: PostsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        recylerview = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search_bar)
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
                adapter = posts.let { PostsAdapter(it) }
                recylerview.adapter = adapter
                if(isMyPost) {
                    adapter.setOnItemClickListener(object : PostsAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val myIntent = Intent(applicationContext, EditPostActivity::class.java)
                            myIntent.putExtra("POST_ID", posts[position].id)
                            startActivity(myIntent)
                        }

                    })
                }
                else{
                    adapter.setOnItemClickListener(object : PostsAdapter.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val myIntent = Intent(applicationContext, ContributionPopUp::class.java)
                            myIntent.putExtra("POST_ID", posts[position].id)
                            startActivity(myIntent)
                        }

                    })
                }
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    private fun filterList(text : String) {
        val filteredList = ArrayList<PostData>()
        for(post in posts){
            if (post.name.lowercase().contains(text.lowercase())){
                filteredList.add(post)
            }
        }
        adapter.newsList = filteredList
    }
}