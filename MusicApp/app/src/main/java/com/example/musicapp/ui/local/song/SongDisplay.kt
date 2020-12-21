package com.example.musicapp.ui.local.song

import com.example.musicapp.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.song_list.*

class SongDisplay (
     val s : Long
): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.txt_song_name.text = "$s"
        viewHolder.txt_artists.text = "James"
    }

    override fun getLayout() = R.layout.song_list

}