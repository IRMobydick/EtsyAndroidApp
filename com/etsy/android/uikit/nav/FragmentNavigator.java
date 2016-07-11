package com.etsy.android.uikit.nav;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.ui.nav.TrackingNavigator;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.util.TabletSupportUtil;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;

public abstract class FragmentNavigator<NavigatorClass extends FragmentNavigator<NavigatorClass>> extends TrackingNavigator<NavigatorClass> {
    private static final String f3190n;
    @NonNull
    protected final FragmentActivity f3191a;
    protected final TabletSupportUtil f3192b;
    protected final FragmentTransactionMode f3193c;
    protected boolean f3194d;
    protected FragmentManager f3195e;
    protected String f3196f;
    protected boolean f3197g;
    protected boolean f3198h;
    @NonNull
    protected Bundle f3199i;
    protected int f3200j;
    protected int f3201k;
    protected AnimationMode f3202l;
    protected SavedState f3203m;

    /* renamed from: com.etsy.android.uikit.nav.FragmentNavigator.1 */
    /* synthetic */ class C09371 {
        static final /* synthetic */ int[] f3992a;

        static {
            f3992a = new int[AnimationMode.values().length];
            try {
                f3992a[AnimationMode.SLIDING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f3992a[AnimationMode.SLIDE_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f3992a[AnimationMode.FADE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f3992a[AnimationMode.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public enum AnimationMode {
        SLIDING,
        FADE,
        NONE,
        SLIDE_BOTTOM
    }

    public enum FragmentTransactionMode {
        ADD,
        REPLACE
    }

    static {
        f3190n = EtsyDebug.m1891a(FragmentNavigator.class);
    }

    public FragmentNavigator(@NonNull FragmentActivity fragmentActivity, FragmentTransactionMode fragmentTransactionMode) {
        this.f3197g = true;
        this.f3198h = false;
        this.f3199i = new Bundle();
        this.f3191a = fragmentActivity;
        this.f3193c = fragmentTransactionMode;
        this.f3192b = new TabletSupportUtil(this.f3191a);
    }

    protected Context m4603a() {
        return this.f3191a;
    }

    public BaseDialogFragment m4607h(Bundle bundle) {
        String string = bundle.getString("dialog_fragment");
        try {
            if (Class.forName(string) != null) {
                Object newInstance = Class.forName(string).newInstance();
                if (newInstance instanceof BaseDialogFragment) {
                    DialogFragment dialogFragment = (BaseDialogFragment) newInstance;
                    this.f3199i.putAll(bundle.getBundle("dialog_fragment_bundle"));
                    dialogFragment.setArguments(this.f3199i);
                    m4604a(dialogFragment);
                    return dialogFragment;
                }
            }
        } catch (InstantiationException e) {
            EtsyDebug.m1911c("Problem showing DialogFragment " + e.getMessage());
        } catch (IllegalAccessException e2) {
            EtsyDebug.m1911c("Problem showing DialogFragment " + e2.getMessage());
        } catch (ClassNotFoundException e3) {
            EtsyDebug.m1911c("Problem showing DialogFragment " + e3.getMessage());
        }
        return null;
    }

    protected void m4604a(DialogFragment dialogFragment) {
        this.f3196f = "dialog";
        if (this.f3192b.m5621a()) {
            dialogFragment.show(this.f3195e != null ? this.f3195e : this.f3191a.getSupportFragmentManager(), this.f3196f);
        } else {
            m4605a((Fragment) dialogFragment);
        }
    }

    protected void m4605a(Fragment fragment) {
        String str;
        int i = 16908290;
        FragmentManager supportFragmentManager = this.f3195e != null ? this.f3195e : this.f3191a.getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (this.f3196f != null) {
            str = this.f3196f;
        } else {
            str = fragment.getClass().getSimpleName() + fragment.hashCode();
        }
        if (!this.f3194d || supportFragmentManager.findFragmentByTag(str) == null) {
            int i2;
            int i3;
            if (this.f3200j > 0) {
                i2 = this.f3200j;
            } else if (this.f3191a.findViewById(this.f3201k) != null) {
                i2 = this.f3201k;
                this.f3198h = true;
            } else {
                i2 = 16908290;
            }
            if (this.f3203m != null) {
                fragment.setInitialSavedState(this.f3203m);
            }
            if (this.f3193c == FragmentTransactionMode.ADD) {
                Fragment findFragmentById;
                Fragment findFragmentById2 = supportFragmentManager.findFragmentById(i2);
                if (findFragmentById2 == null) {
                    if (!this.f3198h) {
                        i = this.f3201k;
                    }
                    findFragmentById = supportFragmentManager.findFragmentById(i);
                } else {
                    findFragmentById = findFragmentById2;
                }
                if (findFragmentById == null || findFragmentById.isHidden()) {
                    this.f3197g = false;
                } else {
                    beginTransaction.hide(findFragmentById);
                }
                m4606a(beginTransaction);
                beginTransaction.add(i2, fragment, str);
            } else if (this.f3193c == FragmentTransactionMode.REPLACE) {
                m4606a(beginTransaction);
                EtsyDebug.m1912c(f3190n, "commitFragment: calling replace for container: " + i2 + ", with new fragment: " + fragment.getClass().getSimpleName() + ", on fragment container: " + fragment.getId());
                beginTransaction.replace(i2, fragment, str);
            }
            if (this.f3197g) {
                beginTransaction.addToBackStack(str);
            }
            beginTransaction.commitAllowingStateLoss();
            str = f3190n;
            StringBuilder append = new StringBuilder().append("commitFragment: after commit, manager count is: ");
            if (supportFragmentManager.getFragments() == null) {
                i3 = 0;
            } else {
                i3 = supportFragmentManager.getFragments().size();
            }
            EtsyDebug.m1912c(str, append.append(i3).toString());
        }
    }

    protected void m4606a(FragmentTransaction fragmentTransaction) {
        switch (C09371.f3992a[this.f3202l.ordinal()]) {
            case Task.NETWORK_STATE_UNMETERED /*1*/:
                fragmentTransaction.setCustomAnimations(R.nav_frag_right_enter, R.nav_frag_right_exit, R.nav_frag_right_pop_enter, R.nav_frag_right_pop_exit);
            case Task.NETWORK_STATE_ANY /*2*/:
                fragmentTransaction.setCustomAnimations(R.nav_frag_bottom_enter, R.nav_frag_bottom_exit, R.nav_frag_bottom_pop_enter, R.nav_frag_bottom_pop_exit);
            case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                fragmentTransaction.setCustomAnimations(R.fade_in, R.fade_out, R.fade_in, R.fade_out);
            default:
                fragmentTransaction.setTransition(-1);
        }
    }
}
