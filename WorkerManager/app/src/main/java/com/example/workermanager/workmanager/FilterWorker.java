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

public class FilterWorker extends Worker {
    public FilterWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            for (int i=0; i<400; i++){
                Log.i("MyTag", "FilterWork: "+i);
            }
            return Result.success();
        }catch (Throwable t){
            return Result.failure();
        }
    }
}
