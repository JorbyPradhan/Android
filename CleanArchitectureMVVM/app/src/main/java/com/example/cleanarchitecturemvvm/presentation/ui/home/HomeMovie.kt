package com.example.cleanarchitecturemvvm.presentation.ui.home


import android.content.Context
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.data.model.Movie
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item.*


class HomeMovie(
    val context : Context,
    val movie : Movie
)   : Item() {

    override fun getLayout() = R.layout.movie_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_rating.text = "${movie.voteAverage}"
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .into(viewHolder.movie_poster)

    }




}