package com.etsy.android.uikit.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.lang.reflect.Field;

public class RetainedNestingDialogFragment extends DialogFragment {
    private FragmentManager mRetainedChildFragmentManager;

    private FragmentManager getRetainedChildFragmentManager() {
        if (this.mRetainedChildFragmentManager == null) {
            this.mRetainedChildFragmentManager = getChildFragmentManager();
        }
        return this.mRetainedChildFragmentManager;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getRetainedChildFragmentManager() != null) {
            try {
                Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
                declaredField.setAccessible(true);
                declaredField.set(this, this.mRetainedChildFragmentManager);
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e2) {
            }
        }
    }
}
