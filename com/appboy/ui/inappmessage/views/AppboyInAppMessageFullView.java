package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.appboy.ui.C0401R;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class AppboyInAppMessageFullView extends AppboyInAppMessageImmersiveBaseView {
    private ImageView mImageView;
    private View mSimpleDraweeView;

    public AppboyInAppMessageFullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews() {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_full_drawee_stub);
            ((GenericDraweeHierarchy) ((SimpleDraweeView) this.mSimpleDraweeView).getHierarchy()).setActualImageScaleType(ScaleType.FIT_CENTER);
            return;
        }
        this.mImageView = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_full_imageview_stub);
        this.mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.mImageView.setAdjustViewBounds(true);
    }

    public List<View> getMessageButtonViews() {
        List<View> arrayList = new ArrayList();
        if (findViewById(C0401R.id.com_appboy_inappmessage_full_button_one) != null) {
            arrayList.add(findViewById(C0401R.id.com_appboy_inappmessage_full_button_one));
        }
        if (findViewById(C0401R.id.com_appboy_inappmessage_full_button_two) != null) {
            arrayList.add(findViewById(C0401R.id.com_appboy_inappmessage_full_button_two));
        }
        return arrayList;
    }

    public View getMessageButtonsView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_full_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_full_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_full_header_text);
    }

    public View getMessageCloseButtonView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_full_close_button);
    }

    public ImageView getMessageImageView() {
        return this.mImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }

    public TextView getMessageIconView() {
        return null;
    }

    public Object getMessageBackgroundObject() {
        return findViewById(C0401R.id.com_appboy_inappmessage_full);
    }
}
