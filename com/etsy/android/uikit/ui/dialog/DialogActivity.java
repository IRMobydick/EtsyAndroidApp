package com.etsy.android.uikit.ui.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public abstract class DialogActivity extends TransparentActivity {
    private static final String TAG;

    /* renamed from: com.etsy.android.uikit.ui.dialog.DialogActivity.1 */
    class C09741 implements OnDismissListener {
        final /* synthetic */ DialogActivity f4077a;

        C09741(DialogActivity dialogActivity) {
            this.f4077a = dialogActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            this.f4077a.goBackDelayed();
        }
    }

    protected abstract void onShowDialog(OnDismissListener onDismissListener);

    static {
        TAG = EtsyDebug.m1891a(DialogActivity.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onShowDialog(new C09741(this));
    }
}
