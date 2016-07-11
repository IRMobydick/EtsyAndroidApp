package com.etsy.android.uikit.ui.core;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.R;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.gcm.Task;

public abstract class TransparentActivity extends TrackingBaseActivity {
    View mRootView;

    /* renamed from: com.etsy.android.uikit.ui.core.TransparentActivity.1 */
    class C09681 extends TrackingOnClickListener {
        final /* synthetic */ TransparentActivity f4066a;

        C09681(TransparentActivity transparentActivity) {
            this.f4066a = transparentActivity;
        }

        public void onViewClick(View view) {
            if (view.getId() == R.window_container) {
                this.f4066a.goBack();
            }
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.core.TransparentActivity.2 */
    class C09692 implements Runnable {
        final /* synthetic */ TransparentActivity f4067a;

        C09692(TransparentActivity transparentActivity) {
            this.f4067a = transparentActivity;
        }

        public void run() {
            this.f4067a.goBack();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.core.TransparentActivity.3 */
    /* synthetic */ class C09703 {
        static final /* synthetic */ int[] f4068a;

        static {
            f4068a = new int[Tint.values().length];
            try {
                f4068a[Tint.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4068a[Tint.DARK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum Tint {
        LIGHT,
        NORMAL,
        DARK
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        this.mRootView = findViewById(R.window_container);
        if (this.mRootView != null) {
            this.mRootView.setOnClickListener(new C09681(this));
        }
    }

    public void goBackDelayed() {
        Handler handler = new Handler();
        if (handler != null) {
            handler.postDelayed(new C09692(this), 200);
        }
    }

    protected void goBack() {
        finish();
        applyExitAnimation();
    }

    public void applyExitAnimation() {
        Intent intent = getIntent();
        if (intent != null) {
            overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
    }

    protected int getLayoutId() {
        return R.activity_transparent_overlay_frame;
    }

    protected void setTint(int i, Tint tint) {
        Drawable background = getWindow().getDecorView().getBackground();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(i));
        switch (C09703.f4068a[tint.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                background.setAlpha(0);
            case Task.NETWORK_STATE_ANY /*2*/:
                background.setAlpha(ValidationUtils.APPBOY_STRING_MAX_LENGTH);
            default:
                background.setAlpha(140);
        }
    }
}
