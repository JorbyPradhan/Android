package com.example.servicetutorial

import android.annotation.SuppressLint
import android.content.*
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*


const val MESSAGE_KEY = "message_key"
class MainActivity : AppCompatActivity() {
    private lateinit var songList: ArrayList<String>
    private var mBound : Boolean = false
    private lateinit var myMusicService: MyMusicService
    companion object{
        var player : MediaPlayer ?= null
    }

    private val mServiceConn : ServiceConnection = object : ServiceConnection{
        @SuppressLint("SetTextI18n")
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myServiceBinder : MyMusicService.MyServiceBinder =
                service as MyMusicService.MyServiceBinder
            myMusicService = myServiceBinder.services
            mBound = true
            if(player!!.isPlaying){
                btn_play.text = "Pause"
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound=false
        }

    }
    private val mReceiver = object : BroadcastReceiver(){
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent?) {
           // val songName = intent?.getStringExtra(MESSAGE_KEY)
            val result = intent?.getStringExtra(MESSAGE_KEY)
            if (result == "done"){
                btn_play.text = "Play"
            }
        }

    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        player = MediaPlayer.create(this, R.raw.savage_love)
        songList = ArrayList()
        songList.add("What do you mean")
        songList.add("Sorry")
        songList.add("Hello")
        songList.add("Sugar")
        button.setOnClickListener {
            Log.i("Myname", "Running Code")
            Toast.makeText(this@MainActivity, "Clicked", Toast.LENGTH_SHORT).show()
            runCode()
        }
        button2.setOnClickListener {
            clearOutput()
        }
        btn_play.setOnClickListener {
            if(mBound){
            // Log.i(TAG, "On Play ${myMusicService.isPlay()}")
            if(player!!.isPlaying){
                player!!.pause()
                btn_play.text = "Play"
            }else{
                val intent = Intent(this@MainActivity, MyMusicService::class.java)
                intent.action = MUSIC_SERVICE_ACTION_START
                startService(intent)
                player!!.start()
                btn_play.text = "Pause"
            }
        }
        }
    }

    private fun clearOutput() {
      //  supportFragmentManager.beginTransaction().replace(R.id.)
      /*  val intent = Intent(this@MainActivity, MyIntentService::class.java)
        stopService(intent)*/
        ItemListDialogFragment.newInstance(5).show(supportFragmentManager, TAG)
    }

    private fun runCode(){
       // log(myMusicService.getValue()!!)
        Log.i("MyTag", "Running Code")
        /*displayProgressBar(true)
        for (song in songList){
            intent = Intent(this, MyIntentService::class.java)
            intent.putExtra(MESSAGE_KEY, song)
            startService(intent)
        }*/
    }
    private fun displayProgressBar(display: Boolean) {
        if (display) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }
    private fun log(s: String){
        txt_text.text = s
    }

    override fun onStart() {
        super.onStart()
        Intent(this@MainActivity, MyMusicService::class.java).also {
            bindService(it, mServiceConn, Context.BIND_AUTO_CREATE)
        }
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(
            mReceiver, IntentFilter(
                MUSIC_COMPLETE
            )
        )
    }

    override fun onStop() {
        super.onStop()
        if(mBound){
            unbindService(mServiceConn)
            mBound=false
        }
        LocalBroadcastManager.getInstance(applicationContext).unregisterReceiver(mReceiver)
    }
}