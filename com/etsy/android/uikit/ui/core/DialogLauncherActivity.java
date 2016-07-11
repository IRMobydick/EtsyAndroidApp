package com.etsy.android.uikit.ui.core;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import com.appboy.support.ValidationUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import com.google.android.gms.gcm.Task;

public abstract class DialogLauncherActivity extends TrackingBaseActivity {
    private BaseDialogFragment mDialogFragment;

    /* renamed from: com.etsy.android.uikit.ui.core.DialogLauncherActivity.1 */
    /* synthetic */ class C09661 {
        static final /* synthetic */ int[] f4056a;

        static {
            f4056a = new int[Tint.values().length];
            try {
                f4056a[Tint.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4056a[Tint.DARK.ordinal()] = 2;
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
        setContentView(R.activity_transparent_overlay_frame);
        setTitle(getIntent().getStringExtra(FindsModule.FIELD_TITLE));
        getAppBarHelper().setNavigationIcon(R.ic_close_24dp);
        if (bundle != null) {
            this.mDialogFragment = (BaseDialogFragment) getSupportFragmentManager().findFragmentByTag("dialog");
        }
        if (this.mDialogFragment == null) {
            this.mDialogFragment = onStartDialogFragment(getIntent().getExtras());
        }
        if (this.mDialogFragment == null) {
            Toast.makeText(this, R.whoops_somethings_wrong, 0).show();
            finish();
        }
    }

    public BaseDialogFragment onStartDialogFragment(Bundle bundle) {
        return null;
    }

    public void finish() {
        super.finish();
        applyExitAnimation();
    }

    public void onBackPressed() {
        if (this.mDialogFragment == null || !this.mDialogFragment.handleBackPressed()) {
            if (this.mDialogFragment != null) {
                this.mDialogFragment.onCancel(this.mDialogFragment.getDialog());
            }
            super.onBackPressed();
        }
    }

    private void applyExitAnimation() {
        Intent intent = getIntent();
        if (intent != null) {
            overridePendingTransition(intent.getIntExtra("NAV_ANIM_BOTTOM_ENTER", 0), intent.getIntExtra("NAV_ANIM_TOP_EXIT", 0));
        }
    }

    protected void setTint(int i, Tint tint) {
        Drawable background = getWindow().getDecorView().getBackground();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(i));
        switch (C09661.f4056a[tint.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                background.setAlpha(0);
            case Task.NETWORK_STATE_ANY /*2*/:
                background.setAlpha(ValidationUtils.APPBOY_STRING_MAX_LENGTH);
            default:
                background.setAlpha(140);
        }
    }
}
