package com.example.flowapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flowapi.R
import com.example.flowapi.model.Post

class PostAdapter (private val context: Context, private var postList:ArrayList<Post>)
    : RecyclerView.Adapter<PostAdapter.MyViewHolder>(){

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val body : TextView = itemView.findViewById(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.each_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val post = postList[position]
        holder.body.text = post.body
    }

    override fun getItemCount(): Int {
       return postList.size
    }
    fun setPost(postList: ArrayList<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

}