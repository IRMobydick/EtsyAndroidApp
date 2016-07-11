package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.appboy.models.MessageButton;
import com.appboy.ui.C0401R;
import com.appboy.ui.inappmessage.IInAppMessageImmersiveView;
import com.appboy.ui.support.StringUtils;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public abstract class AppboyInAppMessageImmersiveBaseView extends AppboyInAppMessageBaseView implements IInAppMessageImmersiveView {
    public abstract List<View> getMessageButtonViews();

    public abstract View getMessageButtonsView();

    public abstract TextView getMessageHeaderTextView();

    public abstract TextView getMessageTextView();

    public AppboyInAppMessageImmersiveBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setMessageButtons(List<MessageButton> list) {
        InAppMessageViewUtils.setButtons(getMessageButtonViews(), getMessageButtonsView(), getContext().getResources().getColor(C0401R.color.com_appboy_inappmessage_button_bg_light), list);
        InAppMessageViewUtils.resetButtonSizesIfNecessary(getMessageButtonViews(), list);
    }

    public void setMessageCloseButtonColor(int i) {
        InAppMessageViewUtils.setViewBackgroundColorFilter(getMessageCloseButtonView(), i, getContext().getResources().getColor(C0401R.color.com_appboy_inappmessage_button_close_light));
    }

    public void setMessageHeaderTextColor(int i) {
        InAppMessageViewUtils.setTextViewColor(getMessageHeaderTextView(), i);
    }

    public void setMessageHeaderText(String str) {
        getMessageHeaderTextView().setText(str);
    }

    public void resetMessageMargins() {
        boolean z = false;
        if (!(getMessageImageView() == null || getMessageImageView().getDrawable() == null)) {
            z = true;
        }
        resetMessageMargins(z);
    }

    public void resetMessageMargins(boolean z) {
        super.resetMessageMargins(z);
        if (StringUtils.isNullOrBlank(getMessageTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageTextView());
        }
        if (StringUtils.isNullOrBlank(getMessageHeaderTextView().getText().toString())) {
            ViewUtils.removeViewFromParent(getMessageHeaderTextView());
        }
        InAppMessageViewUtils.resetMessageMarginsIfNecessary(getMessageTextView(), getMessageHeaderTextView());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        InAppMessageViewUtils.closeInAppMessageOnKeycodeBack();
        return true;
    }
}
