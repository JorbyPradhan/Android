package com.example.servicetutorial

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Message
import android.util.Log

class MyService : Service() {

    private lateinit var mDownloadThread: DownloadThread
    override fun onCreate() {
        super.onCreate()
        mDownloadThread = DownloadThread()
        mDownloadThread.start()
        while (mDownloadThread.handler == null){

        }

    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("onStartCommand", "Running in ${Thread.currentThread().name}")
        val songName = intent?.getStringExtra(MESSAGE_KEY)
        val message = Message.obtain()
        message.obj = songName
        mDownloadThread.handler.sendMessage(message)

/*
        val thread = Thread {
            downloadSong(songName!!)
        }
        thread.start()*/
        return START_REDELIVER_INTENT
    }
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun downloadSong(songName: String){
        Log.i("MyTag","run: starting download")
        try {
            Thread.sleep(4000)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }
        Log.i("MyTag","downloadSong: $songName Downloaded....")
    }
}
