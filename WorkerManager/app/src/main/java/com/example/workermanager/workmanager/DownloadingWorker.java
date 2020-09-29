package com.example.workermanager.workmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadingWorker extends Worker {
    public DownloadingWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            for (int i=0; i<4000; i++) {
                Log.i("MyTag", "DownloadWorker" + i);
            }
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat time = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
                String currentDate = time.format(new Date());
                Log.i("MyTag",currentDate);
               return Result.success();
        }catch (Throwable t){
            return Result.failure();
        }

    }
}
