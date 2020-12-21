package com.example.musicapp

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.musicapp.ui.local.song.MESSAGE_KEY
import com.example.musicapp.ui.local.song.SongsFragment.Companion.player


const val MUSIC_SERVICE_ACTION_START="com.example.musicapp.start"
const val MUSIC_SERVICE_ACTION_STOP="com.example.musicapp.stop"
const val MUSIC_SERVICE_ACTION_PAUSE="com.example.musicapp.pause"
const val MUSIC_SERVICE_ACTION_PLAY="com.example.musicapp.play"
const val MUSIC_COMPLETE = "MusicComplete"
class MyService : Service() {

    private val mBinder: Binder = MyServiceBinder()
    //private var mPlayer: MediaPlayer? = null
    private lateinit var playIntent : PendingIntent
    private lateinit var stopIntent : PendingIntent
    private lateinit var pauseIntent : PendingIntent

    override fun onCreate() {
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
        val services by lazy { MyService() }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            MUSIC_SERVICE_ACTION_START -> {
                showNotification()
            }
            MUSIC_SERVICE_ACTION_PLAY -> {
                play()
            }
            MUSIC_SERVICE_ACTION_PAUSE -> {
                pause()
            }
            MUSIC_SERVICE_ACTION_STOP -> {
                stopForeground(true)
                stopSelf()
            }
            else -> {
                stopSelf()
            }

        }
        return START_NOT_STICKY
    }

    private fun pause() {
        player?.pause()
    }

    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, "channelId")
        Intent(this, MyService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_PLAY
            playIntent = PendingIntent.getService(this, 100, it, 0)
        }

        Intent(this, MyService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_PAUSE
            pauseIntent = PendingIntent.getService(this, 100, it, 0)
        }

        Intent(this, MyService::class.java).also {
            it.action = MUSIC_SERVICE_ACTION_STOP
            stopIntent = PendingIntent.getService(this, 100, it, 0)
        }
        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val resultPendingIntent = PendingIntent.getActivity(
            this,
            1001,
            resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(resultPendingIntent)
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
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return true
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onDestroy() {
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
