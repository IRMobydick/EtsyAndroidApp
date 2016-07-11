package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.ui.C0401R;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.gcm.Task;

public class AppboyInAppMessageSlideupView extends AppboyInAppMessageBaseView {
    private ImageView mImageView;
    private View mSimpleDraweeView;

    /* renamed from: com.appboy.ui.inappmessage.views.AppboyInAppMessageSlideupView.1 */
    /* synthetic */ class C04251 {
        static final /* synthetic */ int[] $SwitchMap$com$appboy$enums$inappmessage$ClickAction;

        static {
            $SwitchMap$com$appboy$enums$inappmessage$ClickAction = new int[ClickAction.values().length];
            try {
                $SwitchMap$com$appboy$enums$inappmessage$ClickAction[ClickAction.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public AppboyInAppMessageSlideupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews() {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_slideup_drawee_stub);
            ((GenericDraweeHierarchy) ((SimpleDraweeView) this.mSimpleDraweeView).getHierarchy()).setActualImageScaleType(ScaleType.FIT_CENTER);
            return;
        }
        this.mImageView = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_slideup_imageview_stub);
    }

    public void setMessageChevron(int i, ClickAction clickAction) {
        switch (C04251.$SwitchMap$com$appboy$enums$inappmessage$ClickAction[clickAction.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                getMessageChevronView().setVisibility(8);
            default:
                InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageChevronView(), i, getContext().getResources().getColor(C0401R.color.com_appboy_inappmessage_chevron));
        }
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_slideup_message);
    }

    public View getMessageBackgroundObject() {
        return findViewById(C0401R.id.com_appboy_inappmessage_slideup);
    }

    public ImageView getMessageImageView() {
        return this.mImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_slideup_icon);
    }

    public View getMessageChevronView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_slideup_chevron);
    }
}
