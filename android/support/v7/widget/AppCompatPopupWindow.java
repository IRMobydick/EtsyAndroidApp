package android.support.v7.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.appcompat.C0234R;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR;
    private static final String TAG = "AppCompatPopupWindow";
    private boolean mOverlapAnchor;

    /* renamed from: android.support.v7.widget.AppCompatPopupWindow.1 */
    final class C02611 implements OnScrollChangedListener {
        final /* synthetic */ Field val$fieldAnchor;
        final /* synthetic */ OnScrollChangedListener val$originalListener;
        final /* synthetic */ PopupWindow val$popup;

        C02611(Field field, PopupWindow popupWindow, OnScrollChangedListener onScrollChangedListener) {
            this.val$fieldAnchor = field;
            this.val$popup = popupWindow;
            this.val$originalListener = onScrollChangedListener;
        }

        public void onScrollChanged() {
            try {
                WeakReference weakReference = (WeakReference) this.val$fieldAnchor.get(this.val$popup);
                if (weakReference != null && weakReference.get() != null) {
                    this.val$originalListener.onScrollChanged();
                }
            } catch (IllegalAccessException e) {
            }
        }
    }

    static {
        COMPAT_OVERLAP_ANCHOR = VERSION.SDK_INT < 21 ? true : COMPAT_OVERLAP_ANCHOR;
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0234R.styleable.PopupWindow, i, 0);
        if (obtainStyledAttributes.hasValue(C0234R.styleable.PopupWindow_overlapAnchor)) {
            setSupportOverlapAnchor(obtainStyledAttributes.getBoolean(C0234R.styleable.PopupWindow_overlapAnchor, COMPAT_OVERLAP_ANCHOR));
        }
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0234R.styleable.PopupWindow_android_popupBackground));
        obtainStyledAttributes.recycle();
        if (VERSION.SDK_INT < 14) {
            wrapOnScrollChangedListener(this);
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @TargetApi(19)
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    public void update(View view, int i, int i2, int i3, int i4) {
        int height;
        if (COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            height = i2 - view.getHeight();
        } else {
            height = i2;
        }
        super.update(view, i, height, i3, i4);
    }

    private static void wrapOnScrollChangedListener(PopupWindow popupWindow) {
        try {
            Field declaredField = PopupWindow.class.getDeclaredField("mAnchor");
            declaredField.setAccessible(true);
            Field declaredField2 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");
            declaredField2.setAccessible(true);
            declaredField2.set(popupWindow, new C02611(declaredField, popupWindow, (OnScrollChangedListener) declaredField2.get(popupWindow)));
        } catch (Throwable e) {
            Log.d(TAG, "Exception while installing workaround OnScrollChangedListener", e);
        }
    }

    public void setSupportOverlapAnchor(boolean z) {
        if (COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = z;
        } else {
            PopupWindowCompat.setOverlapAnchor(this, z);
        }
    }

    public boolean getSupportOverlapAnchor() {
        if (COMPAT_OVERLAP_ANCHOR) {
            return this.mOverlapAnchor;
        }
        return PopupWindowCompat.getOverlapAnchor(this);
    }
}
