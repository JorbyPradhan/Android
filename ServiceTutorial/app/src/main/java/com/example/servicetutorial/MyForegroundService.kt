package com.example.servicetutorial

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showNotification()
        val thread = Thread {
            Log.d(TAG, "onStartCommand: ")
            var i = 0
            while (i <= 10){
                Log.d(TAG, "run : Progress is ${i+1}")
                try {
                    Thread.sleep(1000)
                }catch (e : InterruptedException){
                    e.printStackTrace()
                }
                i++
            }
            Log.d(TAG, "run Download Complete")
            stopForeground(true)
            stopSelf()
        }
        thread.start()
        return START_STICKY
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this,"channelId")
        builder.setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("This is service notification")
            .setContentTitle("Title")
        val notification = builder.build()
        startForeground(123,notification)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
