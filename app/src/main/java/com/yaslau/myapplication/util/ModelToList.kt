package com.yaslau.myapplication.util

import android.util.Log
import com.yaslau.myapplication.data.PostData

class ModelToList(var list: List<PostData>) {

    fun toArrayList(): ArrayList<String>{
        val posts = ArrayList<String>()
        for(post in list){
            var empty = ""
            empty = post.user_name + "\n" + post.address +
                    "\n" + post.phoneNumber + "\n" + post.description +
                    "\n" + post
            posts.add(empty)
        }
        return posts
    }
}