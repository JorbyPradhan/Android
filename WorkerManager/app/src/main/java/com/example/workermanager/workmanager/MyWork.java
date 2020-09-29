package com.example.workermanager.workmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.workermanager.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWork extends Worker {
    public static final String KEY_WORKER = "worker";
    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    //doWork() is background method
    //it return result like failure,retry,success
    @NonNull
    @Override
    public Result doWork() {
        try {
            //get data from main activity like intent.putExtra or bundle
            int count = getInputData().getInt(MainActivity.KEY_COUNT_VAL, 0);
            for (int i=0; i<count; i++){
                Log.i("MyTag", "MyWork: "+i);
            }
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat time = new SimpleDateFormat
                    ("dd/M/yyyy hh:mm:ss");
            String currentDate = time.format(new Date());

            //sent output data to activity or fragment
            //we sent currentDate to main activity
            Data outputData = new Data.Builder().putString(KEY_WORKER,currentDate).build();
            return Result.success(outputData);
        }catch (Throwable t){
            return Result.failure();
        }
    }
}
