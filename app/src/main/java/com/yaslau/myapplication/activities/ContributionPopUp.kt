package com.yaslau.myapplication.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import com.yaslau.myapplication.R
import com.yaslau.myapplication.repository.Repository
import com.yaslau.myapplication.util.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContributionPopUp : Activity() {

    lateinit var id: String
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contribution_pop_up)
        id = intent.getIntExtra("POST_ID", 1).toString()
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        window.setLayout((width/1.5).toInt(), (height/1.5).toInt())
        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = -20
        window.attributes = params
        editText = findViewById(R.id.contribution)
        val button: Button = findViewById(R.id.addContributionButton)
        button.setOnClickListener{addContribution()}
    }

    private fun addContribution() {
        val repo = Repository()
        val sharedPref = SharedPreferencesManager(this)
        val charityName = sharedPref.retrieveName()
        CoroutineScope(Dispatchers.IO).launch {
            repo.addContribution(charityName, id, editText.text.toString())
            withContext(Dispatchers.Main) {
                val myIntent = Intent(applicationContext, PostsActivity::class.java)
                myIntent.putExtra("MY_POSTS", false)
                startActivity(myIntent)
            }
        }
    }
}