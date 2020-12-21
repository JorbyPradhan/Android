package com.example.servicetutorial

import android.os.Looper

class DownloadThread : Thread() {
     lateinit var handler : DownloadHandler
    override fun run() {
        super.run()
        Looper.prepare()
        handler = DownloadHandler()
        Looper.loop()

    }
}