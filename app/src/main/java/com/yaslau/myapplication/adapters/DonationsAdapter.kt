package com.yaslau.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.data.DonationData


class DonationsAdapter(private val newsList : List<DonationData>) : RecyclerView.Adapter<DonationsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.donation_item,
            parent,false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = newsList[position]
        holder.id.text = currentItem.donee_id
        holder.type.text = currentItem.type
        holder.value.text = currentItem.value.toString()
        holder.date.text = currentItem.date

    }


    override fun getItemCount(): Int {

        return newsList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val id : TextView = itemView.findViewById(R.id.id)
        val type : TextView = itemView.findViewById(R.id.type)
        val value : TextView = itemView.findViewById(R.id.value)
        val date : TextView = itemView.findViewById(R.id.donation_date)

    }

}