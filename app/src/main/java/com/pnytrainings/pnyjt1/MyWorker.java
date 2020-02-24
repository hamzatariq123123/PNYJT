package com.pnytrainings.pnyjt1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class MyWorker extends Worker {

    private static final String WORK_RESULT = "work_result";


    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Data outputData;
        Result result;

        if (downloadData()) {
            outputData = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();
            result = Result.success(outputData);
        } else {
            showNotification("WorkManager", "download Failed");
            result = Result.failure();
        }

        return result;
    }

    private Boolean downloadData() {
        // download

        boolean status = false;

        for (int i = 0; i <= 10; i++) {

            if (i == 10) {
                status = true;
                Log.d("count ", "Downloaded %"+i);
                showNotification("WorkManager", "download completed");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return status;
    }


    private void showNotification(String task, String desc) {

        NotificationManager manager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "task_channel";
        String channelName = "task_name";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);


        Random random = new Random();
        int num = random.nextInt();

        manager.notify(num, builder.build());

    }
}
