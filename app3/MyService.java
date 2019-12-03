package com.example.nanorouz.servicedemo2;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.nanorouz.servicedemo2.MyServiceTask.ResultCallback;

public class MyService extends Service {

    private static final String LOG_TAG = "MyService";

    // Handle to notification manager.
    private NotificationManager notificationManager;
    private int ONGOING_NOTIFICATION_ID = 1; // This cannot be 0. So 1 is a good candidate.

    // Motion detector thread and runnable.
    private Thread myThread;
    private MyServiceTask myTask;
    private PowerManager.WakeLock wakeLock;

    public void clearTask() {
        myTask.clear();
    }

    public void exitTask() {
        myTask.exit();
    }

    public boolean didItMove() {
        return myTask.didItMove();
    }

    // Binder class.
    public class MyBinder extends Binder {
        MyService getService() {
            // Returns the underlying service.
            return MyService.this;
        }
    }

    // Binder given to clients
    private final IBinder myBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "Service is being bound");
        // Returns the binder to this service.
        return myBinder;
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(LOG_TAG, "Service is being created");
        // Display a notification about us starting.  We put an icon in the status bar.
        //notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //showMyNotification();

        // Creates the thread running the service.
        myTask = new MyServiceTask(getApplicationContext());
        myThread = new Thread(myTask);
        myThread.start();
    }
    @SuppressLint("InvalidWakeLockTag")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyTag");
        ((PowerManager.WakeLock) wakeLock).acquire();

        Log.i(LOG_TAG, "Received start id " + startId + ": " + intent);
        // We start the task thread.
        if (!myThread.isAlive()) {
            myThread.start();
        }
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Cancel the persistent notification.
        wakeLock.release();
        notificationManager.cancel(ONGOING_NOTIFICATION_ID);
        Log.i(LOG_TAG, "Stopping.");
        // Stops the motion detector.
        myTask.stopProcessing();
        Log.i(LOG_TAG, "Stopped.");
    }


    /**
     * Method that pretends to do something.
     */
    public void doSomething (int i, String s) {
        // We don't actually do anything, we need to ask the service thread to do it
        // for us.
        myTask.doSomething(i, s);
    }

    /**
     * Show a notification while this service is running.
     */


    public void updateResultCallback(ResultCallback resultCallback) {
        myTask.updateResultCallback(resultCallback);
    }

}