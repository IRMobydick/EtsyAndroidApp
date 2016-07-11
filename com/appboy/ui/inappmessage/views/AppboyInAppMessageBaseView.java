package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appboy.ui.C0401R;
import com.appboy.ui.inappmessage.IInAppMessageView;
import com.appboy.ui.support.FrescoLibraryUtils;
import com.appboy.ui.support.StringUtils;
import com.appboy.ui.support.ViewUtils;
import com.facebook.drawee.view.SimpleDraweeView;

public abstract class AppboyInAppMessageBaseView extends RelativeLayout implements IInAppMessageView {
    final boolean mCanUseFresco;

    public abstract Object getMessageBackgroundObject();

    public abstract TextView getMessageIconView();

    public abstract ImageView getMessageImageView();

    public abstract View getMessageSimpleDraweeView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCanUseFresco = FrescoLibraryUtils.canUseFresco(context);
    }

    public void setMessageBackgroundColor(int i) {
        InAppMessageViewUtils.setViewBackgroundColor((View) getMessageBackgroundObject(), i);
    }

    public void setMessageTextColor(int i) {
        InAppMessageViewUtils.setTextViewColor(getMessageTextView(), i);
    }

    public void setMessage(String str) {
        getMessageTextView().setText(str);
    }

    public void setMessageImageView(Bitmap bitmap) {
        InAppMessageViewUtils.setImage(bitmap, getMessageImageView());
    }

    public void setMessageSimpleDrawee(String str) {
        FrescoLibraryUtils.setDraweeControllerHelper((SimpleDraweeView) getMessageSimpleDraweeView(), str, 0.0f, false);
    }

    public void setMessageIcon(String str, int i, int i2) {
        InAppMessageViewUtils.setIcon(getContext(), str, i, i2, getMessageIconView());
    }

    @Deprecated
    public void resetMessageMargins() {
        boolean z = false;
        if (!(getMessageImageView() == null || getMessageImageView().getDrawable() == null)) {
            z = true;
        }
        resetMessageMargins(z);
    }

    public void resetMessageMargins(boolean z) {
        View messageSimpleDraweeView;
        if (this.mCanUseFresco) {
            messageSimpleDraweeView = getMessageSimpleDraweeView();
            View view = (RelativeLayout) findViewById(C0401R.id.com_appboy_stubbed_inappmessage_drawee_view_parent);
        } else {
            messageSimpleDraweeView = getMessageImageView();
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(C0401R.id.com_appboy_stubbed_inappmessage_image_view_parent);
        }
        if (messageSimpleDraweeView != null) {
            if (z) {
                ViewUtils.removeViewFromParent(getMessageIconView());
            } else {
                ViewUtils.removeViewFromParent(messageSimpleDraweeView);
                if (view != null) {
                    ViewUtils.removeViewFromParent(view);
                }
            }
        }
        if (getMessageIconView() != null && StringUtils.isNullOrBlank((String) getMessageIconView().getText())) {
            ViewUtils.removeViewFromParent(getMessageIconView());
        }
    }

    public View getMessageClickableView() {
        return this;
    }

    View getProperViewFromInflatedStub(int i) {
        ((ViewStub) findViewById(i)).inflate();
        if (this.mCanUseFresco) {
            return findViewById(C0401R.id.com_appboy_stubbed_inappmessage_drawee_view);
        }
        return findViewById(C0401R.id.com_appboy_stubbed_inappmessage_image_view);
    }
}
