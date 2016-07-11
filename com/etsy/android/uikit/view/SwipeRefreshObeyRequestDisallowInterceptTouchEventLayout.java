package com.etsy.android.uikit.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout extends SwipeRefreshLayout {
    private boolean mInterceptTouchEvents;

    public SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout(Context context) {
        super(context);
        this.mInterceptTouchEvents = true;
    }

    public SwipeRefreshObeyRequestDisallowInterceptTouchEventLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInterceptTouchEvents = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                this.mInterceptTouchEvents = true;
                return super.onInterceptTouchEvent(motionEvent);
            default:
                if (this.mInterceptTouchEvents) {
                    return super.onInterceptTouchEvent(motionEvent);
                }
                return false;
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
        this.mInterceptTouchEvents = !z;
    }
}
