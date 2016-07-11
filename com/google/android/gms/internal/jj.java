package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import com.google.android.gms.ads.internal.util.client.C1129c;

@TargetApi(19)
@jw
public class jj extends ji {
    private Object f5156g;
    private PopupWindow f5157h;
    private boolean f5158i;

    jj(Context context, lc lcVar, no noVar, jh jhVar) {
        super(context, lcVar, noVar, jhVar);
        this.f5156g = new Object();
        this.f5158i = false;
    }

    private void m6818e() {
        synchronized (this.f5156g) {
            this.f5158i = true;
            if ((this.b instanceof Activity) && ((Activity) this.b).isDestroyed()) {
                this.f5157h = null;
            }
            if (this.f5157h != null) {
                if (this.f5157h.isShowing()) {
                    this.f5157h.dismiss();
                }
                this.f5157h = null;
            }
        }
    }

    protected void m6819a(int i) {
        m6818e();
        super.m6808a(i);
    }

    public void cancel() {
        m6818e();
        super.cancel();
    }

    protected void m6820d() {
        Window window = this.b instanceof Activity ? ((Activity) this.b).getWindow() : null;
        if (window != null && window.getDecorView() != null && !((Activity) this.b).isDestroyed()) {
            View frameLayout = new FrameLayout(this.b);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.addView(this.c.m7247b(), -1, -1);
            synchronized (this.f5156g) {
                if (this.f5158i) {
                    return;
                }
                this.f5157h = new PopupWindow(frameLayout, 1, 1, false);
                this.f5157h.setOutsideTouchable(true);
                this.f5157h.setClippingEnabled(false);
                C1129c.m6185a("Displaying the 1x1 popup off the screen.");
                try {
                    this.f5157h.showAtLocation(window.getDecorView(), 0, -1, -1);
                } catch (Exception e) {
                    this.f5157h = null;
                }
            }
        }
    }
}
