package com.appboy.ui.inappmessage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import com.appboy.Constants;
import com.appboy.ui.C0401R;

public class AppboyInAppMessageButtonView extends Button {
    private static final String TAG;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyInAppMessageButtonView.class.getName()});
    }

    public AppboyInAppMessageButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0401R.styleable.com_appboy_ui_inappmessage_AppboyInAppMessageButtonView);
            for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
                if (obtainStyledAttributes.getIndex(i) == C0401R.styleable.f1049x4a486ca5) {
                    String string = obtainStyledAttributes.getString(i);
                    try {
                        setTypeface(Typeface.createFromAsset(context.getAssets(), string));
                    } catch (Throwable e) {
                        Log.w(TAG, "Error loading custom typeface from: " + string, e);
                    }
                }
            }
            obtainStyledAttributes.recycle();
        } catch (Throwable e2) {
            Log.w(TAG, "Error while checking for custom typeface.", e2);
        }
    }
}
