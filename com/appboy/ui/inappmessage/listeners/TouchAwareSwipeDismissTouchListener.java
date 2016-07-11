package com.appboy.ui.inappmessage.listeners;

import android.view.MotionEvent;
import android.view.View;
import com.appboy.ui.inappmessage.listeners.SwipeDismissTouchListener.DismissCallbacks;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class TouchAwareSwipeDismissTouchListener extends SwipeDismissTouchListener {
    private ITouchListener mTouchListener;

    public interface ITouchListener {
        void onTouchEnded();

        void onTouchStartedOrContinued();
    }

    public TouchAwareSwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        super(view, obj, dismissCallbacks);
    }

    public void setTouchListener(ITouchListener iTouchListener) {
        this.mTouchListener = iTouchListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case Task.NETWORK_STATE_CONNECTED /*0*/:
                if (this.mTouchListener != null) {
                    this.mTouchListener.onTouchStartedOrContinued();
                    break;
                }
                break;
            case Task.NETWORK_STATE_UNMETERED /*1*/:
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                if (this.mTouchListener != null) {
                    this.mTouchListener.onTouchEnded();
                    break;
                }
                break;
        }
        return super.onTouch(view, motionEvent);
    }
}
