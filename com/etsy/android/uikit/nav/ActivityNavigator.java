package com.etsy.android.uikit.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyApplication;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.ui.nav.TrackingNavigator;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.ui.core.DialogLauncherActivity;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public abstract class ActivityNavigator<NavigatorClass extends ActivityNavigator<NavigatorClass>> extends TrackingNavigator<NavigatorClass> {
    protected boolean f3175a;
    protected boolean f3176b;
    protected boolean f3177c;
    protected boolean f3178d;
    protected int f3179e;
    protected String f3180f;
    protected AnimationMode f3181g;
    protected boolean f3182h;
    protected Activity f3183i;
    protected final TabletSupportUtil f3184j;
    protected Fragment f3185k;

    /* renamed from: com.etsy.android.uikit.nav.ActivityNavigator.1 */
    /* synthetic */ class C09361 {
        static final /* synthetic */ int[] f3990a;

        static {
            f3990a = new int[AnimationMode.values().length];
            try {
                f3990a[AnimationMode.SLIDE_RIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3990a[AnimationMode.SLIDE_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3990a[AnimationMode.FADE_SLOW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3990a[AnimationMode.POP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f3990a[AnimationMode.FADE_IN_OUT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f3990a[AnimationMode.DEFAULT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f3990a[AnimationMode.DEFAULT_OUT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f3990a[AnimationMode.ZOOM_IN_OUT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f3990a[AnimationMode.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public enum AnimationMode {
        SLIDE_RIGHT,
        SLIDE_BOTTOM,
        FADE_SLOW,
        FADE_IN_OUT,
        POP,
        DEFAULT,
        DEFAULT_OUT,
        ZOOM_IN_OUT,
        NONE
    }

    protected abstract void m4433c(Intent intent);

    protected ActivityNavigator(Activity activity) {
        this.f3183i = activity;
        this.f3184j = new TabletSupportUtil(activity);
    }

    protected Context m4431a() {
        return this.f3183i;
    }

    protected Intent m4434d(Intent intent) {
        if (this.f3175a) {
            intent.putExtra("NAV_DRAWER", true);
        }
        if (this.f3176b) {
            intent.putExtra("NAV_TOP_LEVEL_DRAWER", true);
        }
        if (this.f3178d) {
            intent.putExtra("NAV_UP_TO_PARENT", true);
        }
        if (!TextUtils.isEmpty(this.f3180f)) {
            intent.putExtra("opened_notification_type", this.f3180f);
        }
        return m4428a(intent, this.f3181g);
    }

    protected void m4432a(Class<? extends DialogLauncherActivity> cls, Class<? extends BaseDialogFragment> cls2, @StringRes int i, Bundle bundle) {
        Intent intent = new Intent(this.f3183i, cls);
        intent.putExtra("dialog_fragment", cls2.getName());
        intent.putExtra(FindsModule.FIELD_TITLE, this.f3183i.getString(i));
        intent.putExtra("dialog_fragment_bundle", bundle);
        if (this.f3184j.m5621a()) {
            this.f3182h = true;
            this.f3181g = AnimationMode.FADE_SLOW;
        } else {
            this.f3181g = AnimationMode.SLIDE_BOTTOM;
        }
        m4433c(intent);
    }

    public static Intent m4428a(Intent intent, AnimationMode animationMode) {
        int i;
        int i2 = 0;
        switch (C09361.f3990a[animationMode.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                i = R.nav_bottom_enter;
                i2 = R.nav_top_exit_right;
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                i = R.nav_bottom_enter;
                i2 = R.nav_top_exit_bottom;
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                i = R.nav_none;
                i2 = R.nav_fade_out;
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                i = R.nav_fade_in;
                i2 = R.nav_top_exit_pop;
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                i = R.nav_fade_in;
                i2 = R.nav_fade_out;
                break;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                i = R.nav_top_zoom_enter;
                i2 = R.nav_top_exit_default;
                break;
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                i = R.nav_top_zoom_enter;
                i2 = R.nav_top_exit_default;
                break;
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                i = R.nav_top_zoom_enter;
                i2 = R.nav_bottom_zoom_exit;
                break;
            default:
                i = 0;
                break;
        }
        intent.putExtra("NAV_ANIM_BOTTOM_ENTER", i);
        intent.putExtra("NAV_ANIM_TOP_EXIT", i2);
        return intent;
    }

    public static void m4429a(Activity activity, AnimationMode animationMode) {
        int i;
        int i2 = 0;
        switch (C09361.f3990a[animationMode.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                i = R.nav_top_enter_right;
                i2 = R.nav_bottom_exit;
                break;
            case Task.NETWORK_STATE_ANY /*2*/:
                i = R.nav_top_enter_bottom;
                i2 = R.nav_bottom_exit;
                break;
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                i = R.nav_fade_in;
                i2 = R.nav_none;
                break;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                i = R.nav_top_enter_pop;
                i2 = R.nav_fade_out;
                break;
            case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                i = R.nav_fade_in;
                i2 = R.nav_fade_out;
                break;
            case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                i = R.nav_top_enter_default;
                i2 = R.nav_top_zoom_exit;
                break;
            case CommonStatusCodes.NETWORK_ERROR /*7*/:
                i = R.nav_empty;
                i2 = R.nav_empty;
                break;
            case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                i = R.nav_bottom_zoom_enter;
                i2 = R.nav_top_zoom_exit;
                break;
            default:
                i = 0;
                break;
        }
        activity.overridePendingTransition(i, i2);
    }

    public static void m4430a(@NonNull FragmentActivity fragmentActivity) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse(EtsyApplication.PACKAGE_PREFIX + fragmentActivity.getPackageName()));
        fragmentActivity.startActivity(intent);
    }
}
