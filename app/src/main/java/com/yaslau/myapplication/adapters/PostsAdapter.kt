package com.yaslau.myapplication.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yaslau.myapplication.R
import com.yaslau.myapplication.data.PostData

class PostsAdapter(var newsList : List<PostData>) : RecyclerView.Adapter<PostsAdapter.MyViewHolder>(){

    private lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.post_item,
            parent,false)

        return MyViewHolder(itemView, listener)

    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = newsList[position]
        holder.name.text = currentItem.name
        holder.address.text = currentItem.address
        holder.phoneNumber.text = currentItem.phone_number
        holder.description.text = currentItem.description
        holder.date.text = currentItem.time_created
        holder.value.text = currentItem.value.toString()
        holder.contributions.text = currentItem.contributions.toString()
        holder.username.text = currentItem.charity_name
        holder.progress.text = "" + currentItem.contributions+ "$/" + currentItem.value +"$"
        holder.progressBar.progress = ((currentItem.contributions.toDouble()/currentItem.value)*100).toInt()
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


    override fun getItemCount(): Int {

        return newsList.size
    }


    class MyViewHolder(itemView : View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.name)
        val address : TextView = itemView.findViewById(R.id.address)
        val phoneNumber : TextView = itemView.findViewById(R.id.phoneNumber)
        val description : TextView = itemView.findViewById(R.id.description)
        val value : TextView = itemView.findViewById(R.id.value)
        val contributions: TextView = itemView.findViewById(R.id.contributions)
        val date : TextView = itemView.findViewById(R.id.date)
        val username : TextView = itemView.findViewById(R.id.username)
        val progress : TextView = itemView.findViewById(R.id.progress)
        val progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)

        init {
            itemView.setOnClickListener(){
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }



}