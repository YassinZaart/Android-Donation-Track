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

class EditPostActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var phonNum: EditText
    lateinit var address: EditText
    lateinit var value: EditText
    lateinit var description: EditText
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)
        id = intent.getIntExtra("POST_ID", 1).toString()
        val editButton: Button = findViewById(R.id.editPostButton)
        val deleteButton: Button = findViewById(R.id.deletePostButton)
        editButton.setOnClickListener { editPost() }
        deleteButton.setOnClickListener { deletePost() }
        name = findViewById(R.id.nameEditText)
        phonNum = findViewById(R.id.phoneEditText)
        address = findViewById(R.id.addressEditText)
        description = findViewById(R.id.descriptionEditText)
        value = findViewById(R.id.valueEditText)
        CoroutineScope(Dispatchers.IO).launch {
            val repo = Repository()
            val post = repo.getPost(id)
            withContext(Dispatchers.Main) {
                if (post != null) {
                    name.setText(post.name)
                    phonNum.setText(post.phone_number)
                    Log.i("hi", ""+post.phone_number)
                    address.setText(post.address)
                    description.setText(post.description)
                    value.setText(post.value.toString())
                }
            }
        }

    }

    private fun deletePost() {
        val repo = Repository()
        CoroutineScope(Dispatchers.IO).launch {
            repo.deletePost(id)
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, PostsActivity::class.java)
                startActivity(myIntent)
            }
        }
    }

    private fun editPost() {
        val repo = Repository()
        val sharedPref = SharedPreferencesManager(this)
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.updatePost(id,charityName, name.text.toString(),
                phonNum.text.toString(), address.text.toString(), value.text.toString(), description.text.toString())
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, PostsActivity::class.java)
                startActivity(myIntent)
            }
        }
    }
}