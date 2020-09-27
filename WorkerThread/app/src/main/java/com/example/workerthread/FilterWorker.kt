package com.example.workerthread

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class FilterWorker (context : Context, params:WorkerParameters) : Worker(context,params){
    override fun doWork(): Result {
        return try {
            for (i in 0..400){
                Log.i("MyTag","FilterWorker $i")
            }
            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }

}