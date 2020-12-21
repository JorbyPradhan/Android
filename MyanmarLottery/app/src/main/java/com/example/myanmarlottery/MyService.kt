package com.example.myanmarlottery

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myanmarlottery.data.notification.OreoNotification

const val MUSIC_SERVICE_ACTION_START = "com.example.myanmarlottery.start"
class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            MUSIC_SERVICE_ACTION_START -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    sendOreoNotification()
                } else
                    showNotification()
            }

        }
        return START_NOT_STICKY
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, "channelId")
        builder.setContentText("I want this number")
            .setContentTitle("Alert")
            .setSmallIcon(R.mipmap.ic_launcher)
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(123, builder.build())
    }

    private fun sendOreoNotification() {
        val oreoNotification  = OreoNotification(this)
        val builder = oreoNotification.getOreoNotification("Alert","I want this number")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
