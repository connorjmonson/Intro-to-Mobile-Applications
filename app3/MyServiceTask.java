package com.example.nanorouz.servicedemo2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;


import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;


public class MyServiceTask implements Runnable {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private SensorEventListener listener;

    public static final String LOG_TAG = "MyService";
    private boolean running;
    private Context context;

    ResultCallback resultCallback;

    public AtomicLong T0 = new AtomicLong();
    public AtomicLong first_accel_time = null;
    private boolean moved = false;


    // Fake variables to pretend we do something.
    private String s = "";
    private int i = 0;

    final Object m = new Object();

    MyServiceTask(Context _context) {
        context = _context;
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //testing
                Log.i(LOG_TAG, "Current Time is = " + ((System.currentTimeMillis() - T0.get()) / 1000));
                Log.d(LOG_TAG, "OnSensorChanged: X: " + event.values[0] + " Y: " + event.values[1]);
                //check for significant change in x or y
                if (Math.abs(event.values[0]) > 1 || Math.abs(event.values[1]-9.81) > 1) {
                    //moved after 30 seconds? if so then set
                    if (System.currentTimeMillis() - T0.get() > 30000) {
                        if (!moved) {
                            first_accel_time = new AtomicLong((System.currentTimeMillis() - T0.get()) / 1000);
                            Log.i(LOG_TAG, "ACCEL TIME SET " + first_accel_time.get());
                            moved = true;
                        }
                    }
                }

            }
            //error if I dont include this!!!
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    @Override
    public void run() {
        T0.set(System.currentTimeMillis());
        running = true;
        Random rand = new Random();

        // accelerometer sensor code
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        //else {
            // no accelerometer
        //}
        mSensorManager.registerListener(listener, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);

        while (running) {
            // Sleep a tiny bit.
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            //if movement has been detected after the initial 30 seconds
            //then we put something into notifyResultCallback
                // I just used the same notifyResultCallback as lecture14 (service demo 2)
                if (moved == true) {
                    boolean c = moved;
                    int r = rand.nextInt(100);
                    // Sends it to the UI thread in MainActivity (if MainActivity
                    // is running).
                    Log.i(LOG_TAG, "Sending random number: " + r);
                    notifyResultCallback(c);

                    if (i > 0) {
                        Log.d("Service", i + " ");
                        i--;
                        if (i < 0) {
                            // Do I ever get here?
                        }
                    }
                }

        }
    }


    public void stopProcessing() {
        // No need to bother with a synchronized statement; booleans are atomically updated.
        running = false;

    }


    public void doSomething(int ii, String ss) {
        // An integer can be always changed atomically.
        i = ii;
        // For a string, we need to synchronize.
        synchronized (m) {
            s = ss;
        }
    }

    /**
     * Call this function to return the integer i to the activity.
     * @param i
     */
    private void notifyResultCallback(boolean i) {
        ServiceResult result = new ServiceResult();
        // If we got a null result, we have no more space in the buffer,
        // and we simply drop the integer, rather than sending it back.
        if (result != null) {
            result.value = i;
            //result.intValue = i;
            Log.i(LOG_TAG, "calling resultCallback for " + result.intValue);
            resultCallback.onResultReady(result);
        }
    }
    public void updateResultCallback(ResultCallback result) {
        Log.i(LOG_TAG, "Adding result callback");
        resultCallback = result;
    }

    //clear the T0 and first_accel_time variables in the task
    public void clear() {
        //first_accel_time.set(0);
        moved = false;
        T0.set(System.currentTimeMillis());
        Log.i(LOG_TAG, "T0 CHANGED: " + T0);
        //stopProcessing();
    }

    public void exit() {
        first_accel_time.set(0);
        moved = false;
        stopProcessing();
    }

    public boolean didItMove() {
        if (first_accel_time != null && System.currentTimeMillis() - T0.get() > 30000){
            moved = true;
        }
        return moved;
    }


    public interface ResultCallback {
        void onResultReady(ServiceResult result);
    }
}
