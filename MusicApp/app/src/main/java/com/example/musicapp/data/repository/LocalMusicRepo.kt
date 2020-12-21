package com.example.musicapp.data.repository

import android.content.ContentResolver
import android.provider.MediaStore

class LocalMusicRepo {
    val songList : ArrayList<String> = ArrayList()
    /*fun getMusic(){
        val contentResolver : ContentResolver = getContentResolver()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val songCursor = contentResolver.query(uri, null, null, null,null)
        if (songCursor != null && songCursor.moveToFirst()){
            val songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val songArtists = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
            do {
                val currentTitle = songCursor.getString(songTitle)
                val currentArtists = songCursor.getString(songArtists)
                songList.add("$currentTitle \n $currentArtists")
            }while (songCursor.moveToNext())
        }
    }*/
}