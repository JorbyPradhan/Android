package com.example.musicapp.ui.local

import com.example.musicapp.R
import com.example.musicapp.ui.local.LocalFragment.Companion.songId
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.song_list.*

class LocalDisplay (
    val sid : Long
    ): Item(){
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.txt_song_name.text = "$sid"
            songId = sid
            viewHolder.txt_artists.text = "James"
        }

        override fun getLayout() = R.layout.song_list

    }