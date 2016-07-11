package com.etsy.android.lib.util;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {
    private static final int ACCELERATION_THRESHOLD = 13;
    private Sensor accelerometer;
    private final bb listener;
    private final be queue;
    private SensorManager sensorManager;

    public ShakeDetector(bb bbVar) {
        this.queue = new be();
        this.listener = bbVar;
    }

    public boolean start(SensorManager sensorManager) {
        if (this.accelerometer != null) {
            return true;
        }
        this.accelerometer = sensorManager.getDefaultSensor(1);
        if (this.accelerometer != null) {
            this.sensorManager = sensorManager;
            sensorManager.registerListener(this, this.accelerometer, 0);
        }
        if (this.accelerometer == null) {
            return false;
        }
        return true;
    }

    public void stop() {
        if (this.accelerometer != null) {
            this.sensorManager.unregisterListener(this, this.accelerometer);
            this.sensorManager = null;
            this.accelerometer = null;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean isAccelerating = isAccelerating(sensorEvent);
        this.queue.m3319a(sensorEvent.timestamp, isAccelerating);
        if (this.queue.m3320b()) {
            this.queue.m3317a();
            this.listener.hearShake();
        }
    }

    private boolean isAccelerating(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        if (Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3))) > 13.0d) {
            return true;
        }
        return false;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
