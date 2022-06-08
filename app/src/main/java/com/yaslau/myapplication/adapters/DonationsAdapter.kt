package com.yaslau.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.data.DonationData


class DonationsAdapter( var newsList : List<DonationData>) : RecyclerView.Adapter<DonationsAdapter.MyViewHolder>(){
     lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.donation_item,
            parent,false)

        return MyViewHolder(itemView, listener)

    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: DonationsAdapter.OnItemClickListener){
        this.listener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = newsList[position]
        holder.id.text = currentItem.donee_id
        holder.description.text = currentItem.description
        holder.name.text = currentItem.name
        holder.value.text = currentItem.value.toString()
        holder.date.text = currentItem.date
        holder.username.text = currentItem.user_name

    }


    override fun getItemCount(): Int {

        return newsList.size
    }

    class MyViewHolder(itemView : View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){

        val id : TextView = itemView.findViewById(R.id.id)
        val name : TextView = itemView.findViewById(R.id.name)
        val description : TextView = itemView.findViewById(R.id.description)
        val value : TextView = itemView.findViewById(R.id.value)
        val date : TextView = itemView.findViewById(R.id.donation_date)
        val username : TextView = itemView.findViewById(R.id.username)

        init {
            itemView.setOnClickListener(){
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }


}