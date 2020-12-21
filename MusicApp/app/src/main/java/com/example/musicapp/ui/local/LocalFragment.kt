package com.example.musicapp.ui.local

import android.content.ContentResolver
import android.content.ContentUris
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.R
import java.util.ArrayList
import com.example.musicapp.ui.local.song.SongDisplay
import com.example.musicapp.ui.local.song.SongsFragment
import com.google.android.material.tabs.TabLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.local_fragment.*
import kotlinx.android.synthetic.main.songs_fragment.*

class LocalFragment : Fragment() {

    companion object {
        fun newInstance() = LocalFragment()
        var player : MediaPlayer?= null
        var songId : Long = 0
    }
    private lateinit var songDetail : ArrayList<Long>
    private lateinit var viewModel: LocalViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.local_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // supportActionBar?.setDisplayShowTitleEnabled(false)
        bindUI()
        tabLayout.addTab(tabLayout.newTab().setText("Songs"))
        tabLayout.addTab(tabLayout.newTab().setText("Artists"))
        tabLayout.addTab(tabLayout.newTab().setText("Albums"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(requireActivity(), childFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        /*tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_music)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_album_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_album_24)*/
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        img_play.setOnClickListener {
            val trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                songId)
            player = MediaPlayer.create(requireContext(),trackUri)
            //player!!.start()
            Toast.makeText(requireContext(),"$songId",Toast.LENGTH_SHORT).show()
        }
    }
    private fun bindUI() {
        songDetail = ArrayList()
        getMusic()
        songId = songDetail[0]
        initRecyclerView(songDetail.toSongList())
    }
    private fun initRecyclerView(s : List<LocalDisplay>) {

        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(s)
        }
        rec_song_play.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }
    private fun getMusic(){
        val contentResolver : ContentResolver = requireContext().contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver.query(uri, null, null, null,null)
        if (songCursor != null && songCursor.moveToFirst()){
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtists = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            val songLocation = songCursor.getColumnIndex(MediaStore.Audio.Media._ID)
            do {
                val currentTitle = songCursor.getString(songTitle)
                val currentArtists = songCursor.getString(songArtists)
                val currentLocation = songCursor.getLong(songLocation)
                songDetail.add(currentLocation)
            }while (songCursor.moveToNext())
        }
    }
    private fun ArrayList<Long>.toSongList(): List<LocalDisplay> {
        return this.map {
            LocalDisplay(it)
        }

    }
}