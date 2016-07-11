package android.support.v13.app;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.Arrays;

public class FragmentCompat {
    static final FragmentCompatImpl IMPL;

    interface FragmentCompatImpl {
        void requestPermissions(Fragment fragment, String[] strArr, int i);

        void setMenuVisibility(Fragment fragment, boolean z);

        void setUserVisibleHint(Fragment fragment, boolean z);

        boolean shouldShowRequestPermissionRationale(Fragment fragment, String str);
    }

    class BaseFragmentCompatImpl implements FragmentCompatImpl {

        /* renamed from: android.support.v13.app.FragmentCompat.BaseFragmentCompatImpl.1 */
        class C00561 implements Runnable {
            final /* synthetic */ Fragment val$fragment;
            final /* synthetic */ String[] val$permissions;
            final /* synthetic */ int val$requestCode;

            C00561(String[] strArr, Fragment fragment, int i) {
                this.val$permissions = strArr;
                this.val$fragment = fragment;
                this.val$requestCode = i;
            }

            public void run() {
                int[] iArr = new int[this.val$permissions.length];
                Context activity = this.val$fragment.getActivity();
                if (activity != null) {
                    PackageManager packageManager = activity.getPackageManager();
                    String packageName = activity.getPackageName();
                    int length = this.val$permissions.length;
                    for (int i = 0; i < length; i++) {
                        iArr[i] = packageManager.checkPermission(this.val$permissions[i], packageName);
                    }
                } else {
                    Arrays.fill(iArr, -1);
                }
                ((OnRequestPermissionsResultCallback) this.val$fragment).onRequestPermissionsResult(this.val$requestCode, this.val$permissions, iArr);
            }
        }

        BaseFragmentCompatImpl() {
        }

        public void setMenuVisibility(Fragment fragment, boolean z) {
        }

        public void setUserVisibleHint(Fragment fragment, boolean z) {
        }

        public void requestPermissions(Fragment fragment, String[] strArr, int i) {
            new Handler(Looper.getMainLooper()).post(new C00561(strArr, fragment, i));
        }

        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String str) {
            return false;
        }
    }

    class ICSFragmentCompatImpl extends BaseFragmentCompatImpl {
        ICSFragmentCompatImpl() {
        }

        public void setMenuVisibility(Fragment fragment, boolean z) {
            FragmentCompatICS.setMenuVisibility(fragment, z);
        }
    }

    class ICSMR1FragmentCompatImpl extends ICSFragmentCompatImpl {
        ICSMR1FragmentCompatImpl() {
        }

        public void setUserVisibleHint(Fragment fragment, boolean z) {
            FragmentCompatICSMR1.setUserVisibleHint(fragment, z);
        }
    }

    class MncFragmentCompatImpl extends ICSMR1FragmentCompatImpl {
        MncFragmentCompatImpl() {
        }

        public void requestPermissions(Fragment fragment, String[] strArr, int i) {
            FragmentCompat23.requestPermissions(fragment, strArr, i);
        }

        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String str) {
            return FragmentCompat23.shouldShowRequestPermissionRationale(fragment, str);
        }
    }

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);
    }

    static {
        if (VERSION.SDK_INT >= 23) {
            IMPL = new MncFragmentCompatImpl();
        } else if (VERSION.SDK_INT >= 15) {
            IMPL = new ICSMR1FragmentCompatImpl();
        } else if (VERSION.SDK_INT >= 14) {
            IMPL = new ICSFragmentCompatImpl();
        } else {
            IMPL = new BaseFragmentCompatImpl();
        }
    }

    public static void setMenuVisibility(Fragment fragment, boolean z) {
        IMPL.setMenuVisibility(fragment, z);
    }

    public static void setUserVisibleHint(Fragment fragment, boolean z) {
        IMPL.setUserVisibleHint(fragment, z);
    }

    public static void requestPermissions(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
        IMPL.requestPermissions(fragment, strArr, i);
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Fragment fragment, @NonNull String str) {
        return IMPL.shouldShowRequestPermissionRationale(fragment, str);
    }
}
