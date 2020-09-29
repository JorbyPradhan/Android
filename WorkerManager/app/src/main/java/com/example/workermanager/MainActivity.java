package com.example.workermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workermanager.workmanager.DownloadingWorker;
import com.example.workermanager.workmanager.FilterWorker;
import com.example.workermanager.workmanager.MyWork;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_COUNT_VAL = "count value" ;
    private Button click;
    private TextView textDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click = findViewById(R.id.btn_click);
        textDisplay = findViewById(R.id.textView);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // setOneTimeWorkRequest();
                setPeriodicWorkRequest();
            }
        });

    }

    private void setPeriodicWorkRequest() {
        //We set after 15 minutes this method again executed
        //system default time is 15 minutes
        //so u set 1 min the method call default time
        //Since u should not set less than 15
        PeriodicWorkRequest periodicWorkRequest =new PeriodicWorkRequest.Builder(DownloadingWorker.class,15, TimeUnit.MINUTES)
            .build();
        WorkManager.getInstance(getApplicationContext()).enqueue(periodicWorkRequest);
    }


    //it work only one time
    private void setOneTimeWorkRequest() {
        WorkManager workManager = WorkManager.getInstance(getApplicationContext());
        //sent data to workerclass
        // we sent value ->100000 to worker class
        Data data = new Data.Builder().putInt(KEY_COUNT_VAL,100000).build();

        //we set constraint like if device is charging
        //the method is execute
        //we also set many more like require network
        //and many more in constraint
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .build();

        //we need to build oneTimeWorkRequest
        //first we need to call which worker class
        //you need to read
        //second if you want to set Contraint and
        //setInputData
        //finally we called build() method*

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWork.class)
                .setConstraints(constraints)
                .setInputData(data)
                .build();

     /*   OneTimeWorkRequest filter = new OneTimeWorkRequest.Builder(FilterWorker.class)
                .build();*/

        //schedule a chain of background jobs like sequence  and parallel
        //sequenceWorks is like that
      /*  workManager
            .beginWith(filter)
            .then(oneTimeWorkRequest)
            .enqueue();*/

        // parallel Works
        /*val parallelWorks  = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingWorker)
            parallelWorks.add(filter)
            workManager
                    .beginWith(parallelWorks)
            .then(compressing)
            .then(oneTimeWorkRequest)
            .enqueue()*/
       /* ArrayList<OneTimeWorkRequest> parallelWorks =new ArrayList<OneTimeWorkRequest>();
        parallelWorks.add(filter);
        parallelWorks.add(oneTimeWorkRequest);
        workManager.enqueue(parallelWorks);*/

        workManager.enqueue(oneTimeWorkRequest);

        //we can also called livedata  and get return data from MyWork class
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        textDisplay.setText(workInfo.getState().name());
                        if (workInfo.getState().isFinished()){
                            //get data from MyWork class
                            Data output = workInfo.getOutputData();
                            String message = output.getString(MyWork.KEY_WORKER);
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}