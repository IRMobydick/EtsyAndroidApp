package com.etsy.android.lib.util;

import android.view.View;
import android.widget.PopupWindow;
import com.etsy.android.lib.R;

/* compiled from: PopUpWindowUtil */
public class ax {
    public static PopupWindow m3278a(View view) {
        PopupWindow popupWindow = new PopupWindow(view.getContext());
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(view.getContext().getResources().getDrawable(R.bg_card_dark));
        popupWindow.setWidth(view.getContext().getResources().getDimensionPixelOffset(R.popup_standard_width));
        popupWindow.setHeight(-2);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        return popupWindow;
    }
}
