package com.example.moviedbmvvm.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbmvvm.R
import com.example.moviedbmvvm.data.db.entity.Movie
import kotlinx.android.synthetic.main.item.view.*

class MovieAdapter(private val context: Context)
    : PagedListAdapter<Movie,MovieAdapter.MyViewHolder>(ItemCallback) {

    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.movie_poster!!
        val title = itemView.movie_title!!
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        //val movie = arr[position]
        holder.title.text = movie!!.title
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+ movie.posterPath).into(holder.img);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return  MyViewHolder(view)
    }

    companion object
        ItemCallback: DiffUtil.ItemCallback<Movie>()
        {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.database_Id == newItem.database_Id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }


}