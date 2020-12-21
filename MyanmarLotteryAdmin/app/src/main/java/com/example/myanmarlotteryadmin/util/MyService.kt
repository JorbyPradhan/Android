package com.example.myanmarlotteryadmin.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myanmarlotteryadmin.R
import com.example.myanmarlotteryadmin.data.notification.OreoNotification

const val MUSIC_SERVICE_ACTION_START = "com.example.myanmarlotteryadmin.start"

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

    private fun sendOreoNotification() {
/*  OreoNotification oreoNotification = new OreoNotification(this);
        Notification.Builder builder = oreoNotification.getOreoNotification(title,body,pendingIntent,
                defaultSound, icon);*/
        val oreoNotification  = OreoNotification(this)
        val builder = oreoNotification.getOreoNotification("Alert","I have this number")
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, "channelId")
        builder.setContentText("I have this number")
            .setContentTitle("Alert")
            .setSmallIcon(R.mipmap.ic_launcher)
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(123, builder.build())
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
