package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.media.TransportMediator;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.overlay.r.1;
import com.google.android.gms.ads.internal.util.client.C1129c;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.internal.jw;

@jw
/* renamed from: com.google.android.gms.ads.internal.overlay.r */
class C1109r implements SensorEventListener {
    private final SensorManager f4554a;
    private final Object f4555b;
    private final Display f4556c;
    private final float[] f4557d;
    private final float[] f4558e;
    private float[] f4559f;
    private Handler f4560g;
    private s f4561h;

    C1109r(Context context) {
        this.f4554a = (SensorManager) context.getSystemService("sensor");
        this.f4556c = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.f4557d = new float[9];
        this.f4558e = new float[9];
        this.f4555b = new Object();
    }

    private void m6072a(int i, int i2) {
        float f = this.f4558e[i];
        this.f4558e[i] = this.f4558e[i2];
        this.f4558e[i2] = f;
    }

    int m6073a() {
        return this.f4556c.getRotation();
    }

    void m6074a(s sVar) {
        this.f4561h = sVar;
    }

    void m6075a(float[] fArr) {
        if (fArr[0] != 0.0f || fArr[1] != 0.0f || fArr[2] != 0.0f) {
            synchronized (this.f4555b) {
                if (this.f4559f == null) {
                    this.f4559f = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.f4557d, fArr);
            switch (m6073a()) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    SensorManager.remapCoordinateSystem(this.f4557d, 2, 129, this.f4558e);
                    break;
                case Task.NETWORK_STATE_ANY /*2*/:
                    SensorManager.remapCoordinateSystem(this.f4557d, 129, TransportMediator.KEYCODE_MEDIA_RECORD, this.f4558e);
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    SensorManager.remapCoordinateSystem(this.f4557d, TransportMediator.KEYCODE_MEDIA_RECORD, 1, this.f4558e);
                    break;
                default:
                    System.arraycopy(this.f4557d, 0, this.f4558e, 0, 9);
                    break;
            }
            m6072a(1, 3);
            m6072a(2, 6);
            m6072a(5, 7);
            synchronized (this.f4555b) {
                System.arraycopy(this.f4558e, 0, this.f4559f, 0, 9);
            }
            if (this.f4561h != null) {
                this.f4561h.zzgt();
            }
        }
    }

    void m6076b() {
        if (this.f4560g == null) {
            Sensor defaultSensor = this.f4554a.getDefaultSensor(11);
            if (defaultSensor == null) {
                C1129c.m6188b("No Sensor of TYPE_ROTATION_VECTOR");
                return;
            }
            HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
            handlerThread.start();
            this.f4560g = new Handler(handlerThread.getLooper());
            if (!this.f4554a.registerListener(this, defaultSensor, 0, this.f4560g)) {
                C1129c.m6188b("SensorManager.registerListener failed.");
                m6078c();
            }
        }
    }

    boolean m6077b(float[] fArr) {
        boolean z = false;
        synchronized (this.f4555b) {
            if (this.f4559f == null) {
            } else {
                System.arraycopy(this.f4559f, 0, fArr, 0, this.f4559f.length);
                z = true;
            }
        }
        return z;
    }

    void m6078c() {
        if (this.f4560g != null) {
            this.f4554a.unregisterListener(this);
            this.f4560g.post(new 1(this));
            this.f4560g = null;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        m6075a(sensorEvent.values);
    }
}
