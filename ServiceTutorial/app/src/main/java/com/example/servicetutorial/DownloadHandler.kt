package com.example.servicetutorial

import android.os.Handler
import android.os.Message
import android.util.Log

class DownloadHandler : Handler() {
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        downloadSong(msg.obj.toString())
    }

    private fun downloadSong(songName: String) {
       try {
          /* val thread = Thread {
               downloadSong(songName!!)
           }
           thread.start()*/
           Thread.sleep(4000)
       }catch (e :InterruptedException){
           e.printStackTrace()
       }
        Log.i("MyTag","downloadSong: $songName Downloaded....")
    }
}