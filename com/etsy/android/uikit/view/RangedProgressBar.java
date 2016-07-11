package com.etsy.android.uikit.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.etsy.android.lib.R;
import com.etsy.android.lib.util.ao;

/* renamed from: com.etsy.android.uikit.view.k */
class RangedProgressBar {
    final Interpolator f4250a;
    final Interpolator f4251b;
    long f4252c;
    long f4253d;
    long f4254e;
    long f4255f;
    float f4256g;
    float f4257h;
    float f4258i;
    boolean f4259j;
    long f4260k;
    final /* synthetic */ RangedProgressBar f4261l;
    private final Paint f4262m;
    private Interpolator f4263n;
    private int f4264o;
    private float f4265p;
    private boolean f4266q;
    private int f4267r;

    public RangedProgressBar(RangedProgressBar rangedProgressBar) {
        this.f4261l = rangedProgressBar;
        this.f4263n = new AccelerateDecelerateInterpolator();
        this.f4250a = new AccelerateDecelerateInterpolator();
        this.f4251b = new DecelerateInterpolator();
        this.f4254e = 77;
        this.f4255f = 0;
        this.f4256g = this.f4261l.mThumbRadius;
        this.f4257h = this.f4261l.mBigThumbRadius;
        this.f4258i = this.f4261l.mThumbRadius;
        this.f4259j = false;
        this.f4262m = new Paint();
        this.f4262m.setColor(rangedProgressBar.getResources().getColor(R.slider_large_circle));
        this.f4262m.setAntiAlias(true);
        this.f4262m.setAlpha(0);
        this.f4267r = -1;
    }

    public void m5661a(int i) {
        this.f4264o = ao.m3260a(i, 0, this.f4261l.mWaypoints.size() - 1);
        m5666d();
    }

    public void m5660a(float f) {
        m5658b(f);
        m5666d();
    }

    public int m5659a() {
        return this.f4264o;
    }

    public float m5663b() {
        return this.f4265p;
    }

    public int m5665c() {
        return ((Integer) this.f4261l.mWaypoints.get(this.f4264o)).intValue();
    }

    private void m5658b(float f) {
        this.f4264o = ao.m3260a(Math.round((f - this.f4261l.mMinPixels) / this.f4261l.mWaypointDistance), 0, this.f4261l.mWaypoints.size() - 1);
    }

    public void m5666d() {
        this.f4265p = ao.m3259a((float) Math.floor((double) ((((float) this.f4264o) * this.f4261l.mWaypointDistance) + this.f4261l.mMinPixels)), this.f4261l.mMinPixels, this.f4261l.mMaxPixels);
    }

    public void m5662a(boolean z) {
        this.f4266q = z;
    }

    public boolean m5667e() {
        return this.f4266q;
    }

    public void m5664b(int i) {
        if (i != this.f4267r || i == -1) {
            this.f4259j = false;
        }
        this.f4267r = i;
    }

    private void m5656a(Canvas canvas, float f) {
        if (!(this.f4259j || this.f4267r == -1)) {
            this.f4252c = System.currentTimeMillis();
            this.f4253d = this.f4252c;
            this.f4259j = true;
            if (this.f4267r == 1) {
                this.f4254e = 0;
                this.f4255f = (long) this.f4262m.getAlpha();
                this.f4256g = this.f4258i;
                this.f4257h = 0.0f;
                this.f4260k = 500;
                this.f4263n = this.f4251b;
            } else if (this.f4267r == 0) {
                this.f4254e = 77;
                this.f4255f = 0;
                this.f4260k = 300;
                this.f4256g = 0.0f;
                this.f4257h = this.f4261l.mBigThumbRadius;
                this.f4258i = this.f4256g;
                this.f4263n = this.f4250a;
            }
        }
        if (this.f4267r != -1 && this.f4259j) {
            if (this.f4252c - this.f4253d >= this.f4260k) {
                this.f4259j = false;
                this.f4267r = -1;
                this.f4262m.setAlpha((int) this.f4254e);
                this.f4258i = this.f4257h;
            } else if (System.currentTimeMillis() >= this.f4252c + 60) {
                this.f4252c = System.currentTimeMillis();
                float a = ao.m3259a(this.f4263n.getInterpolation(((float) (this.f4252c - this.f4253d)) / ((float) this.f4260k)), 0.0f, (float) FullImageView.ASPECT_RATIO_SQUARE);
                this.f4262m.setAlpha(Math.round((((float) (this.f4254e - this.f4255f)) * a) + ((float) this.f4255f)));
                if (this.f4267r == 0) {
                    this.f4258i = (a * (this.f4257h - this.f4256g)) + this.f4256g;
                }
            }
        }
        if (this.f4266q || this.f4267r == 1) {
            canvas.drawCircle(this.f4265p, f, this.f4258i, this.f4262m);
            this.f4261l.invalidate();
        }
    }
}
