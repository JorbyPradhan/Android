package com.example.workerthread

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(context : Context, params :WorkerParameters) : Worker(context,params) {

    companion object{
        const val KEY_WORKER = "key_worker"
    }
    
    //doWork() is background method
    //it return result like failure,retry,success
    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        return try {
            val count = inputData.getInt(MainActivity.KEY_COUNT_VAL, 0)
            for (i in 0 until count){
                Log.i("MyTag","Uploading $i")
            }
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())

            val outputData = Data.Builder().putString(KEY_WORKER,currentDate).build()
            Result.success(outputData)
        }catch (e: Throwable){
            Result.failure()
        }

    }
}