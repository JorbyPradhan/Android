package com.example.workerthread

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DownloadingWorker (context : Context, params:WorkerParameters) : Worker(context,params){
    override fun doWork(): Result {
        return try {
            for (i in 0..4000){
                Log.i("MyTag","FilterWorker $i")
            }
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            Log.i("MyTag",currentDate)
            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }

}