package com.foresee.sdk.tracker.layouts;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import com.appboy.AppboyImageUtils;
import com.etsy.android.uikit.view.ListingFullImageView;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public class InviteViewParams {
    private int alternateOrientationWidth;
    private Configuration configuration;
    private int defaultOrientationWidth;
    private Display display;
    private int foreSeeLogoHeight;
    private int inviteBodyTextSize;
    private int inviteButtonTextSize;
    private int inviteHeadingTextSize;
    private int inviteMargin;
    private int orientation;
    private int screenLayout;
    private int trustELogoHeight;

    public InviteViewParams(Display display, Configuration configuration) {
        this.display = display;
        this.configuration = configuration;
        this.screenLayout = configuration.screenLayout;
        this.orientation = configuration.orientation;
        initDimensions();
    }

    private void initDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.display.getMetrics(displayMetrics);
        int i = this.screenLayout & 15;
        Log.d("FORESEE_SDK_COMMON", String.format("Screen dims (%d x %d), density = %d", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels), Integer.valueOf(displayMetrics.densityDpi)}));
        switch (i) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                Log.d("FORESEE_SDK_LIB", "Small");
                this.defaultOrientationWidth = Math.round(((float) resolveDefaultOrientationWidth(this.display, this.configuration)) * 0.85f);
                this.alternateOrientationWidth = Math.round(((float) resolveDefaultOrientationHeight(this.display, this.configuration)) * 0.9f);
                this.inviteButtonTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteHeadingTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteBodyTextSize = getSizeAsFractionOfScreen(17, this.display);
                this.inviteMargin = getSizeAsFractionOfScreen(10, 20, this.display);
                this.trustELogoHeight = 15;
                this.foreSeeLogoHeight = 36;
            case Task.NETWORK_STATE_ANY /*2*/:
                Log.d("FORESEE_SDK_COMMON", String.format("Normal, %d", new Object[]{Integer.valueOf(displayMetrics.densityDpi)}));
                this.defaultOrientationWidth = Math.round(((float) resolveDefaultOrientationWidth(this.display, this.configuration)) * 0.8f);
                this.alternateOrientationWidth = Math.round(((float) resolveDefaultOrientationHeight(this.display, this.configuration)) * 0.6f);
                this.inviteButtonTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteHeadingTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteBodyTextSize = getSizeAsFractionOfScreen(17, this.display);
                this.inviteMargin = getSizeAsFractionOfScreen(10, 17, this.display);
                if (displayMetrics.densityDpi == AppboyImageUtils.BASELINE_SCREEN_DPI) {
                    this.alternateOrientationWidth = Math.round(((float) resolveDefaultOrientationHeight(this.display, this.configuration)) * ListingFullImageView.ASPECT_RATIO_STANDARD);
                    this.trustELogoHeight = 15;
                    this.foreSeeLogoHeight = 36;
                } else if (displayMetrics.densityDpi == 320) {
                    this.trustELogoHeight = 37;
                    this.foreSeeLogoHeight = 72;
                } else if (displayMetrics.densityDpi == 480) {
                    this.trustELogoHeight = 55;
                    this.foreSeeLogoHeight = 120;
                } else {
                    this.trustELogoHeight = 30;
                    this.foreSeeLogoHeight = 60;
                }
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                Log.d("FORESEE_SDK_LIB", "Large");
                this.defaultOrientationWidth = Math.round(((float) resolveDefaultOrientationWidth(this.display, this.configuration)) * 0.7f);
                this.alternateOrientationWidth = this.defaultOrientationWidth;
                this.inviteButtonTextSize = getSizeAsFractionOfScreen(25, this.display);
                this.inviteHeadingTextSize = getSizeAsFractionOfScreen(25, this.display);
                this.inviteBodyTextSize = getSizeAsFractionOfScreen(22, this.display);
                this.inviteMargin = getSizeAsFractionOfScreen(15, 24, this.display);
                this.trustELogoHeight = 35;
                this.foreSeeLogoHeight = 84;
            case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                Log.d("ForeSee", String.format("XLarge (%d x %d)", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)}));
                this.defaultOrientationWidth = Math.round(((float) resolveDefaultOrientationWidth(this.display, this.configuration)) * 0.5f);
                this.alternateOrientationWidth = Math.round(((float) resolveDefaultOrientationHeight(this.display, this.configuration)) * ListingFullImageView.ASPECT_RATIO_STANDARD);
                this.inviteButtonTextSize = getSizeAsFractionOfScreen(30, this.display);
                this.inviteHeadingTextSize = getSizeAsFractionOfScreen(30, this.display);
                this.inviteBodyTextSize = getSizeAsFractionOfScreen(27, this.display);
                this.inviteMargin = getSizeAsFractionOfScreen(20, 30, this.display);
                this.trustELogoHeight = 60;
                this.foreSeeLogoHeight = 144;
            default:
                this.defaultOrientationWidth = Math.round(((float) resolveDefaultOrientationWidth(this.display, this.configuration)) * 0.66f);
                this.alternateOrientationWidth = this.defaultOrientationWidth;
                this.inviteButtonTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteHeadingTextSize = getSizeAsFractionOfScreen(20, this.display);
                this.inviteBodyTextSize = getSizeAsFractionOfScreen(17, this.display);
                this.inviteMargin = getSizeAsFractionOfScreen(10, 20, this.display);
                this.trustELogoHeight = 30;
                this.foreSeeLogoHeight = 72;
        }
    }

    public int getDefaultOrientationWidth() {
        return this.defaultOrientationWidth;
    }

    public int getAlternateOrientationWidth() {
        return this.alternateOrientationWidth;
    }

    public int getInviteButtonTextSize() {
        return this.inviteButtonTextSize;
    }

    public int getInviteHeadingTextSize() {
        return this.inviteHeadingTextSize;
    }

    public int getInviteBodyTextSize() {
        return this.inviteBodyTextSize;
    }

    public int getInviteMargin() {
        return this.inviteMargin;
    }

    public int getForeSeeLogoHeight() {
        return this.foreSeeLogoHeight;
    }

    public int getTrustELogoHeight() {
        return this.trustELogoHeight;
    }

    protected static int getSizeAsFractionOfScreen(int i, Display display) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return Math.round((((float) displayMetrics.densityDpi) * ((float) i)) / 240.0f);
    }

    protected static int getSizeAsFractionOfScreen(int i, int i2, Display display) {
        return Math.max(getSizeAsFractionOfScreen(i, display), i2);
    }

    private static int resolveDefaultOrientationWidth(Display display, Configuration configuration) {
        int deviceDefaultOrientation = getDeviceDefaultOrientation(display, configuration);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        if (configuration.orientation == deviceDefaultOrientation) {
            return displayMetrics.widthPixels;
        }
        return displayMetrics.heightPixels;
    }

    private static int resolveDefaultOrientationHeight(Display display, Configuration configuration) {
        int deviceDefaultOrientation = getDeviceDefaultOrientation(display, configuration);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        if (configuration.orientation == deviceDefaultOrientation) {
            return displayMetrics.heightPixels;
        }
        return displayMetrics.widthPixels;
    }

    protected static int getDeviceDefaultOrientation(Display display, Configuration configuration) {
        int rotation = display.getRotation();
        if ((rotation == 0 || rotation == 2) && configuration.orientation == 2) {
            return 2;
        }
        if ((rotation == 1 || rotation == 3) && configuration.orientation == 1) {
            return 2;
        }
        return 1;
    }
}
