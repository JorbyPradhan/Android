package com.example.servicetutorial

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.servicetutorial.MainActivity.Companion.player

const val TAG = "MyTag"
const val MUSIC_COMPLETE = "MusicComplete"

class MyMusicService : Service() {
    private val mBinder: Binder = MyServiceBinder()
    //private var mPlayer: MediaPlayer? = null
    private lateinit var playIntent : PendingIntent
    private lateinit var stopIntent : PendingIntent
    private lateinit var pauseIntent : PendingIntent

    override fun onCreate() {
        Log.i(TAG, "onCreate: ")
        super.onCreate()
        //mPlayer = MediaPlayer.create(this, R.raw.savage_love)
         player?.setOnCompletionListener {
            val intent = Intent(MUSIC_COMPLETE)
            intent.putExtra(MESSAGE_KEY, "done")
            LocalBroadcastManager.getInstance(applicationContext)
                .sendBroadcast(intent)
            stopForeground(true)
            stopSelf()
        }
    }

    class MyServiceBinder : Binder() {
        val services: MyMusicService by lazy { MyMusicService() }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            MUSIC_SERVICE_ACTION_START -> {
                Log.i(TAG, "onStartCommand: 1")
                showNotification()
            }
            MUSIC_SERVICE_ACTION_PLAY -> {
                Log.i(TAG, "onStartCommand: 2")
                play()
            }
            MUSIC_SERVICE_ACTION_PAUSE -> {
                Log.i(TAG, "onStartCommand: 3")
                pause()
            }
            MUSIC_SERVICE_ACTION_STOP -> {
                Log.i(TAG, "onStartCommand: 4")
                stopForeground(true)
                stopSelf()
            }
            else -> {
                Log.i(TAG, "onStartCommand: 5")
                stopSelf()
            }

        }
        Log.i(TAG, "onStartCommand: ")
        return START_NOT_STICKY
    }

    private fun pause() {
        player?.pause()
    }

    private fun showNotification() {

        val builder = NotificationCompat.Builder(this, "channelId")
        Intent(this,MyMusicService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_PLAY
            playIntent = PendingIntent.getService(this,100,it,0)
        }

        Intent(this,MyMusicService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_PAUSE
            pauseIntent = PendingIntent.getService(this,100,it,0)
        }

        Intent(this,MyMusicService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_STOP
            stopIntent = PendingIntent.getService(this,100,it,0)
        }
        builder.setContentText("This is service notification")
            .setContentTitle("Title")
            .setSmallIcon(R.mipmap.ic_launcher)
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_play,
                    "Play",
                    playIntent
                )
            )
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_pause,
                    "Pause",
                    pauseIntent
                )
            )
            .addAction(
                NotificationCompat.Action(
                    android.R.drawable.ic_media_pause,
                    "Stop",
                    stopIntent
                )
            )
        val notification = builder.build()
        startForeground(123, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy: ")
        super.onDestroy()
        player?.release()
    }

    //public client methods

    //public client methods
    fun isPlaying(): Boolean {
        return player!!.isPlaying
    }

    private fun play() {
        player?.start()
    }

    /*fun pause() {
        mPlayer!!.pause()
    }*/

}