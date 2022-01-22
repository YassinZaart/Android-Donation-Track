package com.yaslau.myapplication.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.data.PostData

class PostsAdapter(private val newsList : List<PostData>) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.post_item,
            parent,false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = newsList[position]
        holder.name.text = currentItem.user_name
        holder.address.text = currentItem.address
        holder.phoneNumber.text = currentItem.phoneNumber
        holder.description.text = currentItem.description
        holder.date.text = currentItem.time_created

    }


    override fun getItemCount(): Int {

        return newsList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val phoneNumber : TextView = itemView.findViewById(R.id.phoneNumber)
        val description : TextView = itemView.findViewById(R.id.description)
        val date : TextView = itemView.findViewById(R.id.date)

    }

}