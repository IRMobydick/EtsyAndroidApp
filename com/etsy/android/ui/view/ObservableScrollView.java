package com.etsy.android.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import com.google.android.gms.gcm.Task;

public class ObservableScrollView extends ScrollView {
    private ObservableScrollView mCallbacks;
    private float mLastX;
    private float mLastY;
    private float mXDistance;
    private float mYDistance;

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                this.mYDistance = 0.0f;
                this.mXDistance = 0.0f;
                this.mLastX = motionEvent.getX();
                this.mLastY = motionEvent.getY();
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.mXDistance += Math.abs(x - this.mLastX);
                this.mYDistance += Math.abs(y - this.mLastY);
                this.mLastX = x;
                this.mLastY = y;
                if (this.mXDistance > this.mYDistance) {
                    return false;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.mCallbacks != null) {
            this.mCallbacks.m3957a(i2);
        }
    }

    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    public void setCallbacks(ObservableScrollView observableScrollView) {
        this.mCallbacks = observableScrollView;
    }
}
