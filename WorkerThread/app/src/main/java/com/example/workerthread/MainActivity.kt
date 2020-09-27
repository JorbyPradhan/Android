package com.example.workerthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNT_VAL = "key_count"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_click.setOnClickListener {
            //setOneTimeWorkRequest()
            setPeriodicWorkRequest()
        }

    }

    //It work only one time
    private fun setOneTimeWorkRequest(){
        val workManager =  WorkManager.getInstance(applicationContext)

        //sent data to workerclass
        // we sent value ->125 to worker class
        val data : Data = Data.Builder().putInt(KEY_COUNT_VAL,125).build()

        //we set constraint if device is charging
        //the method is excute
        //so we set many more like require network
        //and many more in constraint
        val constraint = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        //we need to build oneTimeWorkRequest
        //first we need to call which worker class
        //you need to read
        //second if you want to set Contraint and
        //setInputData
        //finally we called build() method
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setConstraints(constraint)
            .setInputData(data)
            .build()

        val  downloadingWorker = OneTimeWorkRequest.Builder(DownloadingWorker::class.java)
            .build()

        val filter = OneTimeWorkRequest.Builder(FilterWorker::class.java)
            .build()

        val compressing = OneTimeWorkRequest.Builder(CompressingWorker::class.java)
            .build()

        //we can set two type of work sequence work and
        // parallel Works

        //sequenceWorks is like that
        /*workManager
            .beginWith(filter)
            .then(compressing)
            .then(oneTimeWorkRequest)
            .enqueue()*/
        // parallelWorks is like that
        val parallelWorks  = mutableListOf<OneTimeWorkRequest>()
        parallelWorks.add(downloadingWorker)
        parallelWorks.add(filter)
        workManager
            .beginWith(parallelWorks)
            .then(compressing)
            .then(oneTimeWorkRequest)
            .enqueue()

    
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, {
                text_display.text = it.state.name
                if(it.state.isFinished){
                    val data = it.outputData
                    val message = data.getString(MyWorker.KEY_WORKER)
                    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
                }
            })
    }

    //It work periodically
    private fun setPeriodicWorkRequest(){
        //We set after 15 minutes this method again executed
        //system default time is 15 minutes
        //so u set 1 min the method call default time
        //Since u should not set less than 15 
        val periodicWorkRequest = PeriodicWorkRequest.Builder(DownloadingWorker::class.java,15,TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(periodicWorkRequest)
    }
}