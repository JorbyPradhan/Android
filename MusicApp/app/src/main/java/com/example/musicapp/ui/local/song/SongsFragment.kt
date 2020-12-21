package com.example.musicapp.ui.local.song

import android.Manifest
import android.annotation.SuppressLint
import android.content.*
import android.content.pm.PackageManager
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.IBinder
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.musicapp.MUSIC_COMPLETE
import com.example.musicapp.MUSIC_SERVICE_ACTION_START
import com.example.musicapp.MyService
import com.example.musicapp.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.songs_fragment.*
import java.util.ArrayList

const val MY_REQUEST_PERMISSION = 1
const val MESSAGE_KEY = "message_key"
class SongsFragment : Fragment() {
    private var mBound : Boolean = false
    private lateinit var myMusicService: MyService
    companion object {
        fun newInstance() = SongsFragment()
        var player : MediaPlayer?= null

    }
    private lateinit var songList : ArrayList<Long>
    private lateinit var viewModel: SongsViewModel

    private val mServiceConn : ServiceConnection = object : ServiceConnection {
        @SuppressLint("SetTextI18n")
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myServiceBinder : MyService.MyServiceBinder =
                service as MyService.MyServiceBinder
            myMusicService = myServiceBinder.services
            mBound = true
          /*  if(player!!.isPlaying){
                btn_play.text = "Pause"
            }*/
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
           /* if (result == "done"){
                btn_play.text = "Play"
            }*/
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.songs_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SongsViewModel::class.java)
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), MY_REQUEST_PERMISSION)
            }else{
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_REQUEST_PERMISSION)
            }
        }else{
            bindUI()
        }
        //bindUI()
    }

    private fun bindUI() {
        songList = ArrayList()
        getMusic()
        initRecyclerView(songList.toSongList())
    }

    private fun initRecyclerView(s : List<SongDisplay>) {

        val mAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(s)
        }
        rec_local.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickListener { item, view ->
            val itemList = item as SongDisplay
            val trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            itemList.s)
            player = MediaPlayer.create(requireContext(),trackUri)
            //Uri trackUri = ContentUris.withAppendedId(
            //  android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            //  currSong);
            if(mBound){
                // Log.i(TAG, "On Play ${myMusicService.isPlay()}")
                if(player!!.isPlaying){
                    player!!.pause()
                  //  btn_play.text = "Play"
                }else{
                    val intent = Intent(context, MyService::class.java)
                    intent.action = MUSIC_SERVICE_ACTION_START
                    context?.startService(intent)
                    player!!.start()
                    //btn_play.text = "Pause"
                }
            }
        }
    }
    private fun ArrayList<Long>.toSongList(): List<SongDisplay> {
        return this.map {
            SongDisplay(it)
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
                songList.add(currentLocation)
            }while (songCursor.moveToNext())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            MY_REQUEST_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        bindUI()
                    }
                }else{
                    Toast.makeText(requireContext(),"No Permission granted!",Toast.LENGTH_SHORT).show()
                    requireActivity().finish()
                }
                return
            }
        }
// super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
        Intent(context, MyService::class.java).also {
            context?.bindService(it, mServiceConn, Context.BIND_AUTO_CREATE)
        }
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            mReceiver, IntentFilter(
                MUSIC_COMPLETE
            )
        )
    }

    override fun onStop() {
        super.onStop()
        if(mBound){
            context?.unbindService(mServiceConn)
            mBound=false
        }
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(mReceiver)
    }

  /*  private fun doStuff() {

    }*/
}