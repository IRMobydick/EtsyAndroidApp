package android.support.design.widget;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.C0000R;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BottomSheetDialog extends AppCompatDialog {
    private BottomSheetCallback mBottomSheetCallback;

    /* renamed from: android.support.design.widget.BottomSheetDialog.1 */
    class C00091 implements OnClickListener {
        C00091() {
        }

        public void onClick(View view) {
            if (BottomSheetDialog.this.isShowing()) {
                BottomSheetDialog.this.cancel();
            }
        }
    }

    /* renamed from: android.support.design.widget.BottomSheetDialog.2 */
    class C00102 extends BottomSheetCallback {
        C00102() {
        }

        public void onStateChanged(@NonNull View view, int i) {
            if (i == 5) {
                BottomSheetDialog.this.dismiss();
            }
        }

        public void onSlide(@NonNull View view, float f) {
        }
    }

    public BottomSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BottomSheetDialog(@NonNull Context context, @StyleRes int i) {
        super(context, getThemeResId(context, i));
        this.mBottomSheetCallback = new C00102();
        supportRequestWindowFeature(1);
    }

    protected BottomSheetDialog(@NonNull Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.mBottomSheetCallback = new C00102();
        supportRequestWindowFeature(1);
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(wrapInBottomSheet(i, null, null));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setLayout(-1, -1);
    }

    public void setContentView(View view) {
        super.setContentView(wrapInBottomSheet(0, view, null));
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(wrapInBottomSheet(0, view, layoutParams));
    }

    private View wrapInBottomSheet(int i, View view, LayoutParams layoutParams) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) View.inflate(getContext(), C0000R.layout.design_bottom_sheet_dialog, null);
        if (i != 0 && view == null) {
            view = getLayoutInflater().inflate(i, coordinatorLayout, false);
        }
        FrameLayout frameLayout = (FrameLayout) coordinatorLayout.findViewById(C0000R.id.design_bottom_sheet);
        BottomSheetBehavior.from(frameLayout).setBottomSheetCallback(this.mBottomSheetCallback);
        if (layoutParams == null) {
            frameLayout.addView(view);
        } else {
            frameLayout.addView(view, layoutParams);
        }
        if (shouldWindowCloseOnTouchOutside()) {
            coordinatorLayout.findViewById(C0000R.id.touch_outside).setOnClickListener(new C00091());
        }
        return coordinatorLayout;
    }

    private boolean shouldWindowCloseOnTouchOutside() {
        if (VERSION.SDK_INT < 11) {
            return true;
        }
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(16843611, typedValue, true)) {
            return false;
        }
        if (typedValue.data == 0) {
            return false;
        }
        return true;
    }

    private static int getThemeResId(Context context, int i) {
        if (i != 0) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(C0000R.attr.bottomSheetDialogTheme, typedValue, true)) {
            return typedValue.resourceId;
        }
        return C0000R.style.Theme_Design_Light_BottomSheetDialog;
    }
}
