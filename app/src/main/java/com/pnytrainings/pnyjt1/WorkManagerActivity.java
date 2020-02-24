package com.pnytrainings.pnyjt1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class WorkManagerActivity extends AppCompatActivity {

    Button startWork;
    TextView statusOfWork;
    WorkManager mWorkManager;
    PeriodicWorkRequest periodicWorkRequest;

    MyReciever myReciever;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);

        statusOfWork = findViewById(R.id.textView);
        startWork = findViewById(R.id.button);

        mWorkManager = WorkManager.getInstance();

        Constraints constraints = new Constraints.Builder().
               setRequiresCharging(true).build();

        periodicWorkRequest = new PeriodicWorkRequest.
                Builder(MyWorker.class,15, TimeUnit.MINUTES)
                .setConstraints(constraints).build();

        startWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkManager.enqueue(periodicWorkRequest);
            }
        });

        mWorkManager.getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    WorkInfo.State state = workInfo.getState();
                    statusOfWork.append(state.toString() + "\n");
                }
            }

        });


        myReciever= new MyReciever();
        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReciever,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myReciever);

    }
}
