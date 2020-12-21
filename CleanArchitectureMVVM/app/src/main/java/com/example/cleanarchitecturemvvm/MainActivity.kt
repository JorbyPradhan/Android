package com.example.cleanarchitecturemvvm

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jarvanmo.exoplayerview.extension.MultiQualitySelectorAdapter.MultiQualitySelectorNavigator
import com.jarvanmo.exoplayerview.media.ExoMediaSource.Quality
import com.jarvanmo.exoplayerview.media.SimpleMediaSource
import com.jarvanmo.exoplayerview.media.SimpleQuality
import com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_LANDSCAPE
import com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_PORTRAIT
import com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView
import com.jarvanmo.exoplayerview.ui.ExoVideoPlaybackControlView.ExoClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.watch_fragment.*


class MainActivity : AppCompatActivity() {
    private val modes = arrayOf(
        "RESIZE_MODE_FIT",
        "RESIZE_MODE_FIXED_WIDTH",
        "RESIZE_MODE_FIXED_HEIGHT",
        "RESIZE_MODE_FILL",
        "RESIZE_MODE_ZOOM"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSpinner()
        initControllerMode()
        initVideoView()
        initCustomViews()
    }
    private fun initVideoView() {
        videoView1.isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        videoView1.setBackListener(ExoClickListener { _: View?, isPortrait: Boolean ->
            if (isPortrait) {
                finish()
            }
            false
        })
        videoView1.setOrientationListener { orientation: Int ->
            if (orientation == SENSOR_PORTRAIT) {
                changeToPortrait()
            } else if (orientation == SENSOR_LANDSCAPE) {
                changeToLandscape()
            }
        }

//        videoView.setGestureEnabled(false);
//
//
//        SimpleMediaSource mediaSource = new SimpleMediaSource("http://flv2.bn.netease.com/videolib3/1604/28/fVobI0704/SD/fVobI0704-mobile.mp4");
//        mediaSource.setDisplayName("Apple HLS");
        val mediaSource =
                SimpleMediaSource("https://video.frgn3-1.fna.fbcdn.net/v/t42.9040-2/10000000_646830129234992_711775049878601728_n.mp4?_nc_cat=101&_nc_sid=985c63&efg=eyJ2ZW5jb2RlX3RhZyI6InN2ZV9zZCJ9&_nc_ohc=nJNs4lnaeaMAX9umuK3&_nc_oc=AQmamFms6aeqUhv4-FZa4HWsQkg0Si4o9CKw180NCw_zX0TN_ZINRS8F3TJiZ2Chp3E&_nc_ht=video.frgn3-1.fna&oh=d0424e4091e27f5b04bbdb802f98977a&oe=5F46439E")
        mediaSource.setDisplayName("Apple HLS")

        //demo only,not real multi quality, urls are the same actually
        val qualities: MutableList<Quality> = ArrayList()
        var quality: Quality
        var colorSpan: ForegroundColorSpan? = ForegroundColorSpan(Color.YELLOW)
        var spannableString = SpannableString("1080p")
        spannableString.setSpan(
            colorSpan,
            0,
            spannableString.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        quality = SimpleQuality(spannableString, mediaSource.uri())
        qualities.add(quality)
        spannableString = SpannableString("720p")
        colorSpan = ForegroundColorSpan(Color.LTGRAY)
        spannableString.setSpan(
            colorSpan,
            0,
            spannableString.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        quality = SimpleQuality(spannableString, mediaSource.uri())
        qualities.add(quality)
        mediaSource.setQualities(qualities)
        //        videoView.changeWidgetVisibility(R.id.exo_player_controller_back,View.INVISIBLE);
        videoView1.setMultiQualitySelectorNavigator(MultiQualitySelectorNavigator { quality ->
            quality.uri = Uri.parse("https://video.frgn3-1.fna.fbcdn.net/v/t42.9040-2/10000000_646830129234992_711775049878601728_n.mp4?_nc_cat=101&_nc_sid=985c63&efg=eyJ2ZW5jb2RlX3RhZyI6InN2ZV9zZCJ9&_nc_ohc=nJNs4lnaeaMAX9umuK3&_nc_oc=AQmamFms6aeqUhv4-FZa4HWsQkg0Si4o9CKw180NCw_zX0TN_ZINRS8F3TJiZ2Chp3E&_nc_ht=video.frgn3-1.fna&oh=d0424e4091e27f5b04bbdb802f98977a&oe=5F46439E")
            false
        })
        videoView1.play(mediaSource, false)
    }
    private fun initSpinner() {
       val mAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modes)
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                videoView1.setResizeMode(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
       //adapter.addAll(modes)
        spinner1.adapter = mAdapter
    }
    private fun initControllerMode() {
        applyControllerMode1.setOnClickListener { v: View? ->
            var mode = ExoVideoPlaybackControlView.CONTROLLER_MODE_NONE
            if (all1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_ALL
            }
            if (top1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_TOP
            }
            if (topLandscape1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_TOP_LANDSCAPE
            }
            if (bottom1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_BOTTOM
            }
            if (bottomLandscape1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_BOTTOM_LANDSCAPE
            }
            if (none1.isChecked) {
                mode = mode or ExoVideoPlaybackControlView.CONTROLLER_MODE_NONE
            }
            videoView1.setControllerDisplayMode(mode)
            Toast.makeText(
                this,
                "change controller display mode",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    @SuppressLint("InflateParams")
    private fun initCustomViews() {
        addToTop1.setOnClickListener { v: View? ->
            val view: View =
                layoutInflater.inflate(R.layout.cutom_view_top, null, false)
            videoView1.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_TOP, view)
        }
        addToTopLandscape1.setOnClickListener { v: View? ->
            val view: View =
                layoutInflater.inflate(R.layout.cutom_view_top_landscape, null, false)
            videoView1.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_TOP_LANDSCAPE, view)
        }
        addToBottomLandscape1.setOnClickListener { v: View? ->
            val view: View =
                layoutInflater.inflate(R.layout.cutom_view_bottom_landscape, null, false)
            videoView1.addCustomView(ExoVideoPlaybackControlView.CUSTOM_VIEW_BOTTOM_LANDSCAPE, view)
        }
    }

    private fun changeToPortrait() {

        // WindowManager operation is not necessary
        val attr: WindowManager.LayoutParams = window.attributes
        //        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val window: Window = window
        window.attributes = attr
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        wrapper1.visibility = View.VISIBLE
    }


    private fun changeToLandscape() {

        // WindowManager operation is not necessary
        val lp: WindowManager.LayoutParams = window.attributes
        //        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        val window: Window = window
        window.attributes = lp
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        wrapper1.visibility = View.GONE
    }
    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            videoView1.resume()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT <= 23) {
            videoView1.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            videoView1.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            videoView1.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView1.releasePlayer()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            videoView1.onKeyDown(keyCode, event)
        } else super.onKeyDown(keyCode, event)
    }

}