package com.example.cleanarchitecturemvvm.presentation.ui.home

import android.content.Context
import com.bumptech.glide.Glide
import com.example.cleanarchitecturemvvm.R
import com.example.cleanarchitecturemvvm.data.model.TvShow
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.movie_item.*

class HomeTvShow(
    val context : Context,
    val tvShow : TvShow
)   : Item() {

    override fun getLayout() = R.layout.movie_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500/${tvShow.posterPath}")
            .into(viewHolder.movie_poster)
        viewHolder.txt_rating.text = "${tvShow.voteAverage}"
    }




}