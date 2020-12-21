package com.example.musicapp.ui.local

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musicapp.ui.local.albums.AlbumsFragment
import com.example.musicapp.ui.local.artists.ArtistsFragment
import com.example.musicapp.ui.local.song.SongsFragment

@Suppress("DEPRECATION")
class MyAdapter(
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongsFragment.newInstance()
            }
            1 -> {
                ArtistsFragment.newInstance()
            }
            2 ->{
                AlbumsFragment.newInstance()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}