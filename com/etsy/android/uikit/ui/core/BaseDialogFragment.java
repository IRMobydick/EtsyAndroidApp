package com.etsy.android.uikit.ui.core;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.etsy.android.lib.R;
import com.etsy.android.lib.core.EtsyRequestQueue;
import com.etsy.android.lib.core.img.ImageBatch;
import com.etsy.android.lib.core.posts.EtsyPostManager;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.util.ab;
import com.etsy.android.uikit.IEtsyFragment;
import com.etsy.android.uikit.util.TabletSupportUtil;

public abstract class BaseDialogFragment extends DialogFragment implements IEtsyFragment {
    private static final String TAG;
    private static final float WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE = 0.66f;
    private static final float WINDOW_WIDTH_RATIO_LARGE_PORTRAIT = 0.9f;
    private BaseFragmentDelegate mBaseFragmentDelegate;

    /* renamed from: com.etsy.android.uikit.ui.core.BaseDialogFragment.1 */
    class C09641 extends Dialog {
        final /* synthetic */ BaseDialogFragment f4054a;

        C09641(BaseDialogFragment baseDialogFragment, Context context, int i) {
            this.f4054a = baseDialogFragment;
            super(context, i);
        }

        public void setContentView(View view) {
            super.setContentView(view);
            getWindow().getAttributes().width = this.f4054a.getDialogWidth();
        }
    }

    /* renamed from: com.etsy.android.uikit.ui.core.BaseDialogFragment.2 */
    class C09652 implements OnKeyListener {
        final /* synthetic */ BaseDialogFragment f4055a;

        C09652(BaseDialogFragment baseDialogFragment) {
            this.f4055a = baseDialogFragment;
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4 && keyEvent.getAction() == 1 && this.f4055a.handleBackPressed();
        }
    }

    static {
        TAG = EtsyDebug.m1891a(BaseDialogFragment.class);
    }

    public BaseDialogFragment() {
        this.mBaseFragmentDelegate = new BaseFragmentDelegate(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EtsyDebug.m1914c(TAG, "onCreate: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.m5441a(bundle);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog c09641 = new C09641(this, getActivity(), getTheme());
        if (new TabletSupportUtil(getContext()).m5621a()) {
            c09641.getWindow().requestFeature(1);
            c09641.getWindow().clearFlags(2);
        }
        c09641.setOnKeyListener(new C09652(this));
        c09641.setCanceledOnTouchOutside(false);
        return c09641;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        EtsyDebug.m1914c(TAG, "onActivityCreated: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onResume() {
        super.onResume();
        EtsyDebug.m1914c(TAG, "onResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.m5440a();
    }

    public void onStart() {
        super.onStart();
        EtsyDebug.m1914c(TAG, "onStart: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onPause() {
        super.onPause();
        EtsyDebug.m1914c(TAG, "onPause: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onFragmentResume() {
        EtsyDebug.m1914c(TAG, "onFragmentResume: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDestroy() {
        super.onDestroy();
        EtsyDebug.m1914c(TAG, "onDestroy: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        this.mBaseFragmentDelegate.m5442b();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        EtsyDebug.m1914c(TAG, "onDismiss: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        if (getActivity() != null) {
            ActivityCompat.finishAfterTransition(getActivity());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        EtsyDebug.m1914c(TAG, "onCreateView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
        View inflate = layoutInflater.inflate(R.dialog_fragment_container, viewGroup, false);
        View view = (ViewGroup) inflate.findViewById(R.content_frame);
        View onCreateContentView = onCreateContentView(layoutInflater, view, bundle);
        if (onCreateContentView != view) {
            view.addView(onCreateContentView);
        }
        return inflate;
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        Toolbar toolbar = (Toolbar) view.findViewById(R.app_bar_toolbar);
        if (toolbar != null && (getActivity() instanceof AppCompatActivity)) {
            toolbar.setNavigationIcon(R.ic_close_24dp);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
    }

    public void onDestroyView() {
        this.mBaseFragmentDelegate.m5444c();
        super.onDestroyView();
        EtsyDebug.m1914c(TAG, "onDestroyView: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        EtsyDebug.m1914c(TAG, "onAttach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onDetach() {
        super.onDetach();
        EtsyDebug.m1914c(TAG, "onDetach: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBaseFragmentDelegate.m5443b(bundle);
        EtsyDebug.m1914c(TAG, "onSaveInstanceState: %s (%d)", getClass().getSimpleName(), Integer.valueOf(hashCode()));
    }

    public void goBack() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public int getDialogWidth() {
        ab abVar = new ab(getActivity());
        return (int) ((abVar.m3184g() ? WINDOW_WIDTH_RATIO_LARGE_LANDSCAPE : WINDOW_WIDTH_RATIO_LARGE_PORTRAIT) * ((float) abVar.m3182e()));
    }

    public EtsyRequestQueue getRequestQueue() {
        return this.mBaseFragmentDelegate.m5445d();
    }

    public EtsyPostManager getPostManager() {
        return this.mBaseFragmentDelegate.m5446e();
    }

    public ImageBatch getImageBatch() {
        return this.mBaseFragmentDelegate.m5447f();
    }

    public boolean handleBackPressed() {
        return this.mBaseFragmentDelegate.handleBackPressed();
    }
}
