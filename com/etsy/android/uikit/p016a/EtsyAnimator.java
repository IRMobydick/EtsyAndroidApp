package com.etsy.android.uikit.p016a;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.uikit.util.HardwareAnimatorListener;
import com.etsy.android.uikit.util.ViewTreeObserverHelper;
import com.etsy.android.uikit.view.FullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.etsy.android.uikit.a.b */
public class EtsyAnimator {
    private final View f3904a;
    private ObjectAnimator f3905b;
    private ArrayList<EtsyAnimator> f3906c;
    private long f3907d;
    private HardwareAnimatorListener f3908e;
    private OnGlobalLayoutListener f3909f;
    private OnPreDrawListener f3910g;

    /* renamed from: com.etsy.android.uikit.a.b.1 */
    class EtsyAnimator implements AnimatorUpdateListener {
        final /* synthetic */ EtsyAnimator f3902a;

        EtsyAnimator(EtsyAnimator etsyAnimator) {
            this.f3902a = etsyAnimator;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue(ResponseConstants.HEIGHT)).intValue();
            LayoutParams layoutParams = this.f3902a.f3904a.getLayoutParams();
            layoutParams.height = intValue;
            this.f3902a.f3904a.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: com.etsy.android.uikit.a.b.2 */
    class EtsyAnimator implements OnGlobalLayoutListener {
        final /* synthetic */ EtsyAnimator f3903a;

        EtsyAnimator(EtsyAnimator etsyAnimator) {
            this.f3903a = etsyAnimator;
        }

        public void onGlobalLayout() {
            ViewTreeObserverHelper.m5639b(this.f3903a.f3904a.getViewTreeObserver(), (OnGlobalLayoutListener) this);
            this.f3903a.m5292b();
        }
    }

    private EtsyAnimator(View view) {
        this.f3904a = view;
        this.f3906c = new ArrayList();
        m5286e();
    }

    public static EtsyAnimator m5285a(View view) {
        return new EtsyAnimator(view);
    }

    private void m5286e() {
        this.f3907d = 300;
        this.f3908e = new HardwareAnimatorListener(this.f3904a);
    }

    public EtsyAnimator m5290a(HardwareAnimatorListener hardwareAnimatorListener) {
        this.f3908e = hardwareAnimatorListener;
        return this;
    }

    public EtsyAnimator m5288a(float f, float f2) {
        this.f3906c.add(new EtsyAnimator(6, PropertyValuesHolder.ofFloat("scaleX", new float[]{f, f2})));
        return this;
    }

    public EtsyAnimator m5291b(float f, float f2) {
        this.f3906c.add(new EtsyAnimator(7, PropertyValuesHolder.ofFloat("scaleY", new float[]{f, f2})));
        return this;
    }

    public EtsyAnimator m5289a(int i, int i2) {
        this.f3906c.add(new EtsyAnimator(8, PropertyValuesHolder.ofInt(ResponseConstants.HEIGHT, new int[]{i, i2})));
        return this;
    }

    @Nullable
    ObjectAnimator m5287a() {
        if (this.f3906c.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.f3906c.iterator();
        while (it.hasNext()) {
            EtsyAnimator etsyAnimator = (EtsyAnimator) it.next();
            switch (etsyAnimator.f3911a) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    this.f3904a.setAlpha(0.0f);
                    break;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    this.f3904a.setAlpha(FullImageView.ASPECT_RATIO_SQUARE);
                    break;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    etsyAnimator.f3912b.setFloatValues(new float[]{(float) (-this.f3904a.getHeight())});
                    break;
                case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    etsyAnimator.f3912b.setFloatValues(new float[]{(float) this.f3904a.getHeight()});
                    break;
                case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                    arrayList2.add(new EtsyAnimator(this));
                    break;
            }
            if (etsyAnimator.f3912b != null) {
                arrayList.add(etsyAnimator.f3912b);
            }
        }
        if (arrayList.isEmpty()) {
            this.f3905b = new ObjectAnimator();
            this.f3905b.setTarget(this.f3904a);
        } else {
            this.f3905b = ObjectAnimator.ofPropertyValuesHolder(this.f3904a, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
        }
        this.f3905b.setDuration(this.f3907d);
        this.f3905b.addListener(this.f3908e);
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            this.f3905b.addUpdateListener((AnimatorUpdateListener) it2.next());
        }
        return this.f3905b;
    }

    public void m5292b() {
        ObjectAnimator a = m5287a();
        if (a != null) {
            a.start();
        }
    }

    public void m5293c() {
        this.f3909f = new EtsyAnimator(this);
        ViewTreeObserverHelper.m5636a(this.f3904a.getViewTreeObserver(), this.f3909f);
    }

    public void m5294d() {
        this.f3906c.clear();
        ViewTreeObserverHelper.m5639b(this.f3904a.getViewTreeObserver(), this.f3909f);
        ViewTreeObserverHelper.m5637a(this.f3904a.getViewTreeObserver(), this.f3910g);
        if (this.f3905b != null) {
            this.f3905b.removeAllListeners();
            this.f3905b.cancel();
        }
    }
}
