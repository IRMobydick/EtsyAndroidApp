package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

public class AppboyInAppMessageModalView extends AppboyInAppMessageImmersiveBaseView {
    private ImageView mImageView;
    private View mSimpleDraweeView;

    public AppboyInAppMessageModalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void inflateStubViews() {
        if (this.mCanUseFresco) {
            this.mSimpleDraweeView = getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_modal_drawee_stub);
            ((GenericDraweeHierarchy) ((SimpleDraweeView) this.mSimpleDraweeView).getHierarchy()).setActualImageScaleType(ScaleType.CENTER_INSIDE);
            return;
        }
        this.mImageView = (ImageView) getProperViewFromInflatedStub(C0401R.id.com_appboy_inappmessage_modal_imageview_stub);
    }

    public void setModalFrameColor(Integer num) {
        InAppMessageViewUtils.setModalFrameColor(findViewById(C0401R.id.com_appboy_inappmessage_modal_frame), num);
    }

    public void setMessageBackgroundColor(int i) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(findViewById(C0401R.id.com_appboy_inappmessage_modal), i, getContext().getResources().getColor(C0401R.color.com_appboy_inappmessage_background_light));
    }

    public List<View> getMessageButtonViews() {
        List<View> arrayList = new ArrayList();
        if (findViewById(C0401R.id.com_appboy_inappmessage_modal_button_one) != null) {
            arrayList.add(findViewById(C0401R.id.com_appboy_inappmessage_modal_button_one));
        }
        if (findViewById(C0401R.id.com_appboy_inappmessage_modal_button_two) != null) {
            arrayList.add(findViewById(C0401R.id.com_appboy_inappmessage_modal_button_two));
        }
        return arrayList;
    }

    public View getMessageButtonsView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_modal_button_layout);
    }

    public TextView getMessageTextView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_modal_message);
    }

    public TextView getMessageHeaderTextView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_modal_header_text);
    }

    public View getMessageClickableView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_modal);
    }

    public View getMessageCloseButtonView() {
        return findViewById(C0401R.id.com_appboy_inappmessage_modal_close_button);
    }

    public TextView getMessageIconView() {
        return (TextView) findViewById(C0401R.id.com_appboy_inappmessage_modal_icon);
    }

    public Drawable getMessageBackgroundObject() {
        return findViewById(C0401R.id.com_appboy_inappmessage_modal).getBackground();
    }

    public ImageView getMessageImageView() {
        return this.mImageView;
    }

    public View getMessageSimpleDraweeView() {
        return this.mSimpleDraweeView;
    }
}
