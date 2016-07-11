package com.foresee.sdk.tracker.layouts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.foresee.sdk.tracker.StringsProvider;
import com.foresee.sdk.tracker.drawables.InvitationBackground;
import com.foresee.sdk.tracker.styles.FsrColor;
import com.foresee.sdk.tracker.util.DisplayUtil;
import java.io.IOException;
import java.io.InputStream;

public abstract class InviteView extends LinearLayout {
    protected int alternateOrientationWidth;
    private final InviteButtonsView buttonsView;
    protected SurveyInviteClickHandler callback;
    protected int defaultOrientationWidth;
    private Display display;
    protected final DisplayUtil displayUtil;
    private final InviteFooterView footerView;
    protected BodyView inviteBodyView;
    private final LinearLayout inviteLayout;
    protected final LayoutParams inviteLayoutParams;
    protected StringsProvider stringsProvider;
    protected InviteViewParams viewParams;

    protected abstract String getAcceptButtonText(StringsProvider stringsProvider);

    protected abstract BodyView getBodyView(Context context, StringsProvider stringsProvider);

    protected abstract String getDeclineButtonText(StringsProvider stringsProvider);

    public InviteView(Context context, InviteViewParams inviteViewParams, String str, SurveyInviteClickHandler surveyInviteClickHandler, StringsProvider stringsProvider) {
        super(context);
        this.viewParams = inviteViewParams;
        this.defaultOrientationWidth = inviteViewParams.getDefaultOrientationWidth();
        this.alternateOrientationWidth = inviteViewParams.getAlternateOrientationWidth();
        this.displayUtil = new DisplayUtil(getResources().getDisplayMetrics());
        this.display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.callback = surveyInviteClickHandler;
        this.stringsProvider = stringsProvider;
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        setGravity(17);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        setBackgroundColor(FsrColor.BLACK_TRANSPARENT.getColor());
        this.inviteLayoutParams = new LayoutParams(-1, -2);
        this.inviteLayoutParams.gravity = 17;
        this.inviteLayoutParams.height = -2;
        if (context.getResources().getConfiguration().orientation == InviteViewParams.getDeviceDefaultOrientation(this.display, getResources().getConfiguration())) {
            this.inviteLayoutParams.width = this.defaultOrientationWidth;
        } else {
            this.inviteLayoutParams.width = this.alternateOrientationWidth;
        }
        this.inviteLayout = new LinearLayout(context);
        this.inviteLayout.setLayoutParams(this.inviteLayoutParams);
        this.inviteLayout.setOrientation(1);
        this.inviteLayout.setBackgroundDrawable(new InvitationBackground((float) this.displayUtil.toDP(10), (float) this.displayUtil.toDP(5)));
        int inviteMargin = inviteViewParams.getInviteMargin();
        this.displayUtil.setPadding(this.inviteLayout, inviteMargin, inviteMargin, inviteMargin, inviteMargin);
        this.inviteLayout.addView(new InviteHeaderView(inviteViewParams, str, context));
        this.inviteBodyView = getBodyView(context, stringsProvider);
        this.inviteLayout.addView(this.inviteBodyView);
        this.buttonsView = new InviteButtonsView(context, inviteViewParams, getAcceptButtonText(stringsProvider), getDeclineButtonText(stringsProvider), surveyInviteClickHandler);
        this.inviteLayout.addView(this.buttonsView);
        this.footerView = new InviteFooterView(this, context);
        this.inviteLayout.addView(this.footerView, new LayoutParams(-1, -2));
        addView(this.inviteLayout);
    }

    public void enable() {
        this.buttonsView.setEnabled(true);
        this.inviteBodyView.setEnabled(true);
    }

    public void disable() {
        this.buttonsView.setEnabled(false);
        this.inviteBodyView.setEnabled(false);
    }

    private static Bitmap loadBitmap(String str, ClassLoader classLoader, Context context) {
        try {
            Bitmap decodeStream;
            InputStream resourceAsStream = classLoader.getResourceAsStream(str);
            if (resourceAsStream != null) {
                decodeStream = BitmapFactory.decodeStream(resourceAsStream);
                resourceAsStream.close();
            } else {
                decodeStream = null;
            }
            if (decodeStream != null) {
                return decodeStream;
            }
            int identifier = context.getResources().getIdentifier(str.substring(0, str.indexOf(".")), "drawable", context.getPackageName());
            if (identifier == 0) {
                return decodeStream;
            }
            resourceAsStream = context.getResources().openRawResource(identifier);
            if (resourceAsStream == null) {
                return decodeStream;
            }
            decodeStream = BitmapFactory.decodeStream(resourceAsStream);
            resourceAsStream.close();
            return decodeStream;
        } catch (IOException e) {
            return null;
        }
    }

    protected void setAcceptButtonText(String str) {
        this.buttonsView.setYesButtonText(str);
    }

    public void setTitle(String str) {
        this.inviteBodyView.setTitle(str);
    }

    public void setBody(String str) {
        this.inviteBodyView.setBodyText(str);
    }

    public void onOrientationChanged(int i) {
        if (i == InviteViewParams.getDeviceDefaultOrientation(this.display, getResources().getConfiguration())) {
            this.inviteLayoutParams.width = this.defaultOrientationWidth;
            return;
        }
        this.inviteLayoutParams.width = this.alternateOrientationWidth;
    }
}
